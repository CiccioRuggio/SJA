import subprocess
subprocess.run("clear", shell=True)

class CarSteering:
    def engage_steering(self):
        print("Car steering engaged.")

class BikeSteering:
    def engage_steering(self):
        print("Bike steering engaged.")

class Vehicle:
    def __init__(self, steering):
        self.steering = steering

    def steer(self):
        self.steering.engage_steering()


car_s = CarSteering()
bike_s = BikeSteering()

v1 = Vehicle(car_s)
v2 = Vehicle(bike_s)

v1.steering.engage_steering()
v2.steering.engage_steering()