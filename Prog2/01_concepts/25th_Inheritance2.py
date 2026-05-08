import subprocess
subprocess.run("clear", shell=True)

class Car:
    def __init__(self, doors = 5):
        self.doors = doors
    def accelerate(self):
        print(f"Car is accelerating")

class Vehicle:
    def __init__(self, model, year):
        self.model = model
        self.year = year
    
    def accelerate(self):
        print(f"Vehicle is accelerating")

class SuperCar(Vehicle, Car):
    color = "red"

pandino = SuperCar("Panda", 1996)

# la classe SuperCar eredità contemporaneamente da Vehicle e Car. In questo caso richiamando un metodo comune a entrambe le classi padri sarà runnato quello della prima classe scritta nella dichiarazione (ex. ClasseFiglia(ClassePadre1, ClassePadre2); in caso di omonimia di metodi avranno priorità quelli di ClassePadre1)
pandino.accelerate()