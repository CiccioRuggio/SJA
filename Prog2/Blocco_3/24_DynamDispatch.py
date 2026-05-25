import subprocess
subprocess.run("clear", shell=True)

class Car:
    def action(self):
        print('Car is running')
    
class Vehicle:
    def action(self):
        print('Vehicle is running')

class Motorcycle:
    def action(self):
        print('Motorcycle is running')
        
c1 = Car()
v1 = Vehicle()
m1 = Motorcycle()

printin = [c1,v1,m1]

for el in printin:
    el.action()