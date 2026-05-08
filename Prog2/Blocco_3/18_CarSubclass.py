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

class Car(Vehicle):
    def __init__(self, model="Unknown", year=0, doors = 5):
        super().__init__(model, year)
        self.doors = doors

c1 = Car("Mazda", 2020, 4)

print(c1.describe())