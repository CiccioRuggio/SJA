from flask import Flask, request, render_template, redirect, url_for

import pandas as pd
from joblib import load

model = None
while not model:
    try:
        model = load("modello_mutuo.joblib")
    except Exception as e:
        print('JobLib model NOT found\n' + e)
    

app = Flask(__name__)

REQUIRED = ['Gender',
 'Married',
 'Dependents',
 'Education',
 'Self_Employed',
 'ApplicantIncome',
 'CoapplicantIncome',
 'LoanAmount',
 'Loan_Amount_Term',
 'Credit_History',
 'Property_Area']

def predici_mutuo(dati_cliente: dict):
    try:
        nuovo = pd.DataFrame([dati_cliente])
        classe = model.predict(nuovo)[0]
        probabilita = model.predict_proba(nuovo)[0, 1]
        return {
            "predizione_binaria": int(classe),
            "esito": "APPROVATO" if classe == 1 else "RIFIUTATO",
            "probabilita_approvazione": round(float(probabilita), 3)
        }
    except Exception as e:
        print('ERROR in predict function\n' + e)

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/predict', methods=['GET', 'POST'])
def predict():
    if request.method == 'POST':
        dati_cliente = {
            'Gender': request.form['Gender'],
            'Married': request.form['Married'],
            'Dependents': request.form['Dependents'],
            'Education': request.form['Education'],
            'Self_Employed': request.form['Self_Employed'],
            'ApplicantIncome': int(request.form['ApplicantIncome']),
            'CoapplicantIncome': float(request.form['CoapplicantIncome']),
            'LoanAmount': float(request.form['LoanAmount']),
            'Loan_Amount_Term': float(request.form['Loan_Amount_Term']),
            'Credit_History': float(request.form['Credit_History']),
            'Property_Area': request.form['Property_Area'],
        }
        risultato = predici_mutuo(dati_cliente)
        if risultato['predizione_binaria'] == 1:
            return redirect(url_for('success'))
        else:
            return redirect(url_for('denied'))
    return render_template('index.html')

@app.route('/success')
def success():
    return render_template('success.html')

@app.route('/denied')
def denied():
    return render_template('denied.html')

if __name__ == '__main__':
    app.run(debug=True)