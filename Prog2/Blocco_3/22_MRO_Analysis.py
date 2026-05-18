import subprocess
subprocess.run("clear", shell=True)

class Flyer:
    def __init__(self, name):
        self.name = name
    
    def fly(self):
        return f"{self.name} is flying."

class Swimmer:
    def __init__(self, name):
        self.name = name
        
    def fly(self):
        return f"{self.name} is swimming."

class Duck(Swimmer, Flyer):
    def __init__(self, name):
        self.name = name
    
d1 = Duck("Donald")
print(d1.fly())
print(Duck.mro())