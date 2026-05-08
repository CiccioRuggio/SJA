import subprocess
subprocess.run("clear", shell=True)

class Vehicle:
    type = "Car"
    
    def __init__(self, model):
        self.model = model
    

v1 = Vehicle("Audi - A1")
v2 = Vehicle("Volkswagen - Polo")

print(v1.type, v1.model)
print(v2.type, v2.model)