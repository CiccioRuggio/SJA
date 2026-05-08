import subprocess
subprocess.run("clear", shell=True)

class StartMode:
    def start(self):
        print("Starting in Compose mode")
        
class StartModeElectric(StartMode):
    def start(self):
        print("Switching to Electric Mode")

class StartModePedal(StartMode):
    def start(self):
        print("Press the pedal to ignite")

class Vehicle:
    def __init__(self, model, startMode):
        self.model = model
        self.startMode = startMode
    
    def startVehicle(self):
        self.startMode.start()

m1 = Vehicle("KTM", StartModePedal())
c1 = Vehicle("Panda", StartModeElectric())

m1.startVehicle()
c1.startVehicle()

m1.startMode.start()
