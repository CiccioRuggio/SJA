import subprocess
subprocess.run("clear", shell=True)

class Vehicle:
    type = "Car"
    
    def __init__(self, model):
        self.model = model
    

v1 = Vehicle("Audi - A1")
v2 = Vehicle("KTM - Duke 390")

v2.type = "Moto"
print(v1.type, v1.model)
print(v2.type, v2.model)
# il dict qui NON stampa type car perchè è un attributo di classe e non di istanza
print(v1.__dict__)
# il dict qui stampa anche type car perchè è stato assegnato in istanza
print(v2.__dict__)
# qui il dict stampa anche type car perchè láttributo diventa sia di classe che di istanza
print(Vehicle.__dict__)