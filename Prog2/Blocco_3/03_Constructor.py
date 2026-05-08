import subprocess
subprocess.run("clear", shell=True)

class Student:
    def __init__(self, name, grade):
        self.name = name
        self.grade = grade
        print(f"Student {self.name} created successfully with grade {self.grade}")

s1 = Student("Mimmo", 27)
s2= Student("Gianni", 30)