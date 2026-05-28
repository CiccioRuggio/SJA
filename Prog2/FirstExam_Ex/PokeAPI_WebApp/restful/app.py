import subprocess, json, requests
# subprocess.run("clear", shell=True)
from flask import Flask, request, render_template, redirect, url_for
from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate

# Tutti i tipi Pokémon standard (usati per il filtro per tipo in index)
POKEMON_TYPES = [
    'normal', 'fire', 'water', 'electric', 'grass', 'ice',
    'fighting', 'poison', 'ground', 'flying', 'psychic', 'bug',
    'rock', 'ghost', 'dragon', 'dark', 'steel', 'fairy'
]

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///creatures.db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

db = SQLAlchemy(app)
migrate = Migrate(app, db)


# ── MODEL ──────────────────────────────────────────────────────────────────────
# Rappresenta la tabella 'creatures' nel database SQLite.
# id_key: chiave primaria autoincrementale generata dal DB
# id:     numero del pokedex nazionale (usato per la ricerca)
# types e stats vengono salvati come stringhe JSON (json.dumps / json.loads)

class Creature(db.Model):
    __tablename__ = 'creatures'
    id_key = db.Column(db.Integer, primary_key=True, autoincrement=True)    # la key personale del db, utilizzata autoincrementale per gestire l'ordine di creazione dei record e per rendere totalmente univoco e separato ogni singolo record, a prescindere dall'id di partenza
    id     = db.Column(db.Integer, nullable=False)
    name   = db.Column(db.String(80), nullable=False, unique=True)
    height = db.Column(db.Integer, nullable=False)
    weight = db.Column(db.Integer, nullable=False)
    types  = db.Column(db.Text, nullable=False)                             # es. '["fire", "flying"]'
    stats  = db.Column(db.Text, nullable=False)                             # es. '{"hp": 45, "attack": 49}'
    sprite      = db.Column(db.Text, nullable=True)                         # URL gif animata (Gen V) o png statico
    is_favorite = db.Column(db.Boolean, nullable=False, default=False)      # stella preferiti

    # Accetta **dict sia dai dati API (types come lista) che dal DB (types come stringa JSON)
    def __init__(self, **kwargs):
        kwargs.setdefault('is_favorite', False)
        types = kwargs.get('types', [])
        stats = kwargs.get('stats', {})
        kwargs['types'] = json.dumps(types) if not isinstance(types, str) else types
        kwargs['stats'] = json.dumps(stats) if not isinstance(stats, str) else stats
        super().__init__(**kwargs)

    # Helper per i template Jinja2
    def get_types(self):
        return json.loads(self.types)

    def get_stats(self):
        return json.loads(self.stats)


# ── READER HTTP ────────────────────────────────────────────────────────────────
# Richiede i dati alla PokeAPI e li normalizza in un dizionario piatto.
# Non scrive nel database, non restituisce HTML.
# Restituisce (data_dict, None) oppure (None, messaggio_errore)

class PokemonReader:
    POKEPATH = "https://pokeapi.co/api/v2/pokemon/"

    def fetch(self, name_or_id):
        try:
            response = requests.get(self.POKEPATH + str(name_or_id))
            if response.status_code == 404:
                return None, f"Pokemon '{name_or_id}' not found in the API."
            response.raise_for_status()
            return self._normalize(response.json()), None
        except requests.exceptions.ConnectionError:
            return None, "Network error: could not reach the API."
        except requests.exceptions.HTTPError as e:
            return None, f"HTTP error: {e}"
        except Exception as e:
            return None, f"Unexpected error: {e}"

    def _normalize(self, data):
        # Prova prima la gif animata di Gen V, poi fallback all'immagine statica
        try:
            sprite = data['sprites']['versions']['generation-v']['black-white']['animated']['front_default']
            if not sprite:
                raise KeyError
        except (KeyError, TypeError):
            sprite = data['sprites'].get('front_default')

        return {
            'id':     data['id'],
            'name':   data['name'],
            'height': data['height'],
            'weight': data['weight'],
            'types':  [t['type']['name'] for t in data['types']],
            'stats':  {s['stat']['name']: s['base_stat'] for s in data['stats']},
            'sprite': sprite,
        }


# ── VIEWS (routes Flask) ───────────────────────────────────────────────────────

# GET / → mostra lista pokemon con ricerca, ordinamento, filtro per tipo e preferiti
# Query params: q (testo/id), sort (id|id_key|name), types (lista), exact (on|off), favorites (on|off)
@app.route('/')
def index():
    q              = request.args.get('q', '').strip()
    sort           = request.args.get('sort', 'id')
    reverse        = request.args.get('reverse') == 'on'
    selected_types = request.args.getlist('types')
    exact          = request.args.get('exact')     == 'on'
    favorites      = request.args.get('favorites') == 'on'

    query = Creature.query

    # Filtro preferiti (applicato in SQLAlchemy)
    if favorites:
        query = query.filter(Creature.is_favorite == True)

    # Ricerca per nome o per id numerico (logica analoga al case 3 di PokeApi/main.py)
    if q:
        if q.isdigit():
            query = query.filter(
                (Creature.name.ilike(f'%{q}%')) | (Creature.id == int(q))
            )
        else:
            query = query.filter(Creature.name.ilike(f'%{q}%'))

    # Ordinamento (con supporto ordine inverso)
    if sort == 'id_key':
        col = Creature.id_key
    elif sort == 'name':
        col = Creature.name
    else:
        col = Creature.id
    query = query.order_by(col.desc() if reverse else col)

    creatures = query.all()

    # Filtro per tipo (fatto in Python perché types è JSON serializzato)
    if selected_types:
        if exact:
            # Il pokemon deve avere ESATTAMENTE i tipi selezionati (né di più, né di meno)
            selected_set = set(selected_types)
            creatures = [c for c in creatures if set(c.get_types()) == selected_set]
        else:
            # Il pokemon deve avere ALMENO UNO dei tipi selezionati
            creatures = [c for c in creatures if any(t in c.get_types() for t in selected_types)]

    return render_template('index.html',
                           creatures=creatures,
                           q=q,
                           sort=sort,
                           reverse=reverse,
                           selected_types=selected_types,
                           exact=exact,
                           favorites=favorites,
                           all_types=POKEMON_TYPES)


# POST /favorite/<id_key> → toglla is_favorite e torna alla pagina precedente (con tutti i filtri attivi)
@app.route('/favorite/<int:id_key>', methods=['POST'])
def toggle_favorite(id_key):
    creature = Creature.query.get_or_404(id_key)
    creature.is_favorite = not creature.is_favorite
    db.session.commit()
    return redirect(request.referrer or url_for('index'))


# GET  /add → mostra il form con campo nome_pokemon
# POST /add → chiama API, salva nel DB, redirect a index
@app.route('/add', methods=['GET', 'POST'])
def add():
    if request.method == 'POST':
        name = request.form['nome_pokemon'].lower().strip()
        data, error = PokemonReader().fetch(name)
        if error:
            return render_template('add.html', error=error)
        existing = Creature.query.filter_by(name=data['name']).first()
        if existing:
            return render_template('add.html', error=f"'{data['name']}' is already in the database.")
        creature = Creature(**data)
        db.session.add(creature)
        db.session.commit()
        return redirect(url_for('index'))
    return render_template('add.html', error=None)


# GET  /edit/<id_key> → mostra form con dati attuali del pokemon
# POST /edit/<id_key> → aggiorna height e weight nel DB, redirect a index
@app.route('/edit/<int:id_key>', methods=['GET', 'POST'])
def edit(id_key):
    creature = Creature.query.get_or_404(id_key)
    if request.method == 'POST':
        creature.height = int(request.form['height'])
        creature.weight = int(request.form['weight'])
        db.session.commit()
        return redirect(url_for('index'))
    return render_template('edit.html', creature=creature)


# ── ERROR HANDLER ──────────────────────────────────────────────────────────────

@app.errorhandler(404)
def page_not_found(error):
    return render_template('404.html'), 404


if __name__ == '__main__':
    app.run(debug=True)


'''
=====================================
Setup iniziale (una sola volta):

  flask db init
  flask db migrate -m "creatures table"
  flask db upgrade

Per avviare l'app:
  python app.py

oppure:
  flask run --debug

http://127.0.0.1:5000/
=====================================
'''
