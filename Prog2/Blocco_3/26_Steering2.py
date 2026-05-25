import subprocess
subprocess.run("clear", shell=True)

class CarSteering:
    def __init__(self, name):
        self.name = name
    
    def engage_steering(self):
        print(f"{self.name} (Car) steering engaged.")

class BikeSteering:
    def __init__(self, name):
        self.name = name

    def engage_steering(self):
        print(f"{self.name} (Bike) steering engaged.")

class Vehicle:
    def __init__(self, steering):
        self.steering = steering

    def steer(self):
        self.steering.engage_steering()


v1 = Vehicle(CarSteering('Vehicle 1'))

print("Before substitution:")
v1.steer()

v1.steering = BikeSteering('Vehicle 2')

print("After substitution:")
v1.steer()
