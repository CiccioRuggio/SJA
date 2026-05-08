import subprocess
subprocess.run("clear", shell=True)

class Person():
    name = "Unknown"
    age = 0
    
    def __init__(self, name, age):
        self.name = name
        self.age = age
    
    def greet(self):
        print(f"Hello {self.name}!")
        
    def travel(self, start, end):
        print(f"{self.name} traveled from: {start} to {end}")
        
    def getInfo(self):
        return f"Name: {self.name}\nAge: {self.age}"

p1 = Person("Gigino", 16)
print(p1.getInfo())