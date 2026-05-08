import subprocess
subprocess.run("clear", shell=True)

class Vehicle:
    def __init__(self, model, year):
        self.model = model
        self.year = year
        self.broken = False

class VehicleError(Exception):
    def __init__(self, message = "vehicle broken"):
        super().__init__(message)
        
a = input()

if Vehicle.doors == 9:
    raise VehicleError("Carro non valido")
else:
    print(f"Il carro ha {Vehicle.doors} porte")