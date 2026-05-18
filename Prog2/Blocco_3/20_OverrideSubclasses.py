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
    
    def returnNumber(self):
        return 5

class Car(Vehicle):
    def __init__(self, model="Unknown", year=0, doors = 5):
        super().__init__(model, year)
        self.doors = doors
    
    def returnNumber(self):
        return 10
        
class ElectricCar(Car):
    def __init__(self, model="Unknown", year=0, doors = 5, watt = 250):
        super().__init__(model, year, doors)
        self.watt = watt
    
    def returnNumber(self):
        return 15
    
v = Vehicle("Fiat", 2008)
c = Car("Tesla Model S", 2022, 4)
ec = ElectricCar("Tesla", 2023, 4, 650)

print(v.returnNumber())
print(c.returnNumber())
print(ec.returnNumber())