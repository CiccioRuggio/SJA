import subprocess
subprocess.run("clear", shell=True)

# DYNAMIC DISPATCH: ha senso ed è utile chiamare metodi di classi differenti con lo stesso nome perchè in caso di iterazioni ad esempio tra elementi differenti si potrà richiamare il metodo una volta sola (esempio alla riga 30)

class Car:
    def accelerate(self):
        print(f"Car is accelerating")

class Vehicle:
    def accelerate(self):
        print(f"Vehicle is accelerating")
        
class Moto:
    def accelerate(self):
        print(f"Moto is accelerating")
        
class Bike:
    def accelerate(self):
        print(f"Bike is accelerating")

list = []
v1 = Vehicle()
c1 = Car()
m1 = Moto()
b1 = Bike()

list.append(v1)
list.append(c1)
list.append(m1)
list.append(b1)

for el in list:
    el.accelerate()