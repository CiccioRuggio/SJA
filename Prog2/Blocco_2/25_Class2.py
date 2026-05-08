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

p1 = Person("Gigino", 16)
p2 = Person("Alfredo", 88)
p1.greet()
p2.greet()