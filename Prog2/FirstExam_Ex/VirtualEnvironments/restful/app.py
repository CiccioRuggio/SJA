from flask import Flask, request, render_template, redirect, url_for
from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///products.db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

db = SQLAlchemy(app)
migrate = Migrate(app, db)


class Product(db.Model):
	name = db.Column(db.String(80), primary_key=True)
	price = db.Column(db.Float, nullable=False)
	description = db.Column(db.Text, nullable=True)
	image_url = db.Column(db.String(255), nullable=True)

''' 
1) flask db init        # una sola volta nel progetto, crea cartella migrations, 
inizializza alembic

2)flask db migrate -m "messaggio"
  scandaglia app.py e visualizza tutti i model presenti. 
3)flask db upgrade

'''	

# ✅ Mostra tutti i prodotti
@app.route('/')
def index():
	products = Product.query.all()
	return render_template('index.html', products=products)


# Aggiungi nuovo prodotto
@app.route('/add', methods=['GET', 'POST'])
def add_product():
	if request.method == 'POST':
		new_product = Product(
			name=request.form['name'],
			price=float(request.form['price']),
			description=request.form.get('description', ''),
			image_url=request.form.get('image_url', '')
		)
		db.session.add(new_product)
		db.session.commit()
		return redirect(url_for('index'))
	return render_template('form.html', product=None)


# ✏️ Modifica prodotto esistente
@app.route('/edit/<string:name>', methods=['GET', 'POST'])
def edit_product(name):
	product = Product.query.get_or_404(name)
	if request.method == 'POST':
		product.price = float(request.form['price'])
		product.description = request.form.get('description', '')
		product.image_url = request.form.get('image_url', '')
		db.session.commit()
		return redirect(url_for('index'))
	return render_template('form.html', product=product)


# ❌ Elimina prodotto
@app.route('/delete/<string:name>', methods=['POST'])
def delete_product(name):
	product = Product.query.get_or_404(name)
	db.session.delete(product)
	db.session.commit()
	return redirect(url_for('index'))


# 📄 Mostra singolo prodotto
@app.route('/product/<string:name>')
def view_product(name):
	product = Product.query.get_or_404(name)
	return render_template('product.html', product=product)


#ERRORHANDLER
@app.errorhandler(404)
def page_not_found(error):
    return render_template("404.html"), 404


if __name__ == '__main__':
	app.run(debug=True) ##permette di fare le modifiche!!!

	'''
	=====================================
.venv\Scripts\Activate.ps1 
flask db init

flask db migrate -m "creazione db"

flask db upgrade
=====================================

python app.py

flask run non fa modifiche

http://127.0.0.1:5000/


'''