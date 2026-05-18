import subprocess
subprocess.run("clear", shell=True)

import json

class Person:
    def __init__(self, name, age):
        self.name = name
        self.age = age

path = "Prog2/02_phase/assets/data.json"

p1 = Person("Flavio", "++")
p2 = Person("Gianmarco", 27)
p3 = Person("Andrea", 19)

pList = [p1.__dict__,p2.__dict__,p3.__dict__]

# data = 5
with open(path, "w") as f:
    # for el in pList:
    #     json.dump(el.__dict__, f)
    json.dump(pList, f) #il dump passa NECESSARIAMENTE un dict, quindi nel caso di una lista di oggetti serve passare ogni oggetto come dict