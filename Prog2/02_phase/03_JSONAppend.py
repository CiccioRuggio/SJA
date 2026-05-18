import subprocess
subprocess.run("clear", shell=True)

import json

class Person:
    def __init__(self, name, age):
        self.name = name
        self.age = age

path = "Prog2/02_phase/assets/data.json"

p1 = Person("Mirko", 92)
p2 = Person("Lenzo", 26)
p3 = Person("Richard", 24)

pList = [p1.__dict__,p2.__dict__,p3.__dict__]
with open(path, "r") as fr:
    value = json.load(fr)
    
for el in pList:
    value.append(el) # da sistemare, aggiunge una nuova lista invece di aggiungere i singoli elementi alla lista originale
with open(path, "w") as f:
    # for el in pList:
    #     json.dump(el.__dict__, f)
    json.dump(value, f) #il dump passa NECESSARIAMENTE un dict, quindi nel caso di una lista di oggetti serve passare ogni oggetto come dict