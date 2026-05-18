import subprocess
subprocess.run("clear", shell=True)

import json

path = "Prog2/02_phase/assets/data.json"

class Person:
    def __init__(self, name, age):
        self.name = name
        self.age = age
        
with open(path, "r") as f:
    value = json.load(f)
    
  
print(value)

for el in value:
    p = Person(el['name'], el['age'])
    print(f"{p.name,p.age}")

# print(type(p1),p1.__dict__)
# print(p1.name,p1.age)