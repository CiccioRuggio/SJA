import subprocess
subprocess.run("clear", shell=True)

class Vehicle:
    def __init__(self, model, year):
        self.model = model
        self.year = year
    
    def accelerate(self):
        print(f"{self.model} is accelerating")
    def turning(self, angle):
        print(f"{self.model} is turning by {angle} degrees")

class Car(Vehicle):
    def __init__(self, model, year, doors = 5):
        super().__init__(model, year)
        self.doors = doors
    
    #OVERRIDE
    def accelerate(self):
        print(f"{self.model} is accelerating, and this car has {self.doors} doors")
    def ex_accelerate(self):
        super().accelerate()

class Motorcycle(Vehicle):
    def  __init__(self, model, year, transmission_type = "Automatic"):
        super().__init__(model, year)
        self.transmission_type  = transmission_type

c1 = Car("Fiat", 2012, 7)
c2 = Car("BMW", 2023)

c1.accelerate()
c2.turning(25)