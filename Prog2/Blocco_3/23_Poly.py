import subprocess
subprocess.run("clear", shell=True)

class Vehicle:
    model = ""
    year = 0
    
    def __init__(self, model = "Unknown", year = 0):
        self.model = model
        self.year = year
    
    def describe(self):
        return self.__dict__
    
    def steer(self):
        return f"Vehicle {self.model} is steering."
    
class Car(Vehicle):
    def __init__(self, model="Unknown", year=0, doors = 5):
        super().__init__(model, year)
        self.doors = doors
    
    def steer(self):
        return f"Car {self.model} is steering."

class Car(Vehicle):
    def __init__(self, model="Unknown", year=0, doors = 5):
        super().__init__(model, year)
        self.doors = doors
    
    def steer(self):
        return f"Car {self.model} is steering."

class Motorcycle(Vehicle):
    def __init__(self, model="Unknown", year=0):
        super().__init__(model, year)
    
    def steer(self):
        return f"Moto {self.model} is steering."

c1 = Car("Mazda", 2022, 5)
c2 = Car("Honda", 1999, 5)
c3 = Car("Range Rover", 2005, 7)

m1 = Motorcycle("Yamaha", 2019)
m2 = Motorcycle("Honda", 2025)
m3 = Motorcycle("MG", 2020)

vList = [c1,m1,c2,c3,m2,m3]

for v in vList:
    print(v.steer())