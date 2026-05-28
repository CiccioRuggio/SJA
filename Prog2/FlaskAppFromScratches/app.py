import subprocess
subprocess.run("clear", shell=True)

'''
LIBRERIE DA METTERE SEMPRE
pip install flask
pip install flask-sqlalchemy
pip install flask-migrate
pip install flask-restful
'''

from flask import Flask, request, render_template, redirect, url_for
from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate

app = Flask(__name__)

app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///products.db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

db = SQLAlchemy(app)

migrate = Migrate(app, db)

class Product(db.Model):
    id = db.Column(db.Integer, primary_key=True, autoincrement = True)
    name = db.Column(db.String(80), nullable=False)
    price = db.Column(db.Float, nullable = False)
    description = db.Column(db.Text)
    img_url = db.Column(db.String(255))
    
'''
UNA VOLTA STRUTTURATO IL DB VA CREATO E VA INSTRADATA L'APP FLASK
COMANDI TERMINALE:
flask db init
flask db migrate -m "Initial migration."            da -m in poi è opzionale, serve per inserire un messaggio di log con la migrazione
flask db upgrade                                    PER MODIFICARE QUALCOSA
'''

# ROUTING
@app.route('/')
def index():
    products = Product.query.all()
    return render_template('index.html', products=products)



if __name__ == '__main__':
    app.run(debug=True)