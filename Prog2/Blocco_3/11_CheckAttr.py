import subprocess
subprocess.run("clear", shell=True)

class Student:
    def __init__(self, name, grade):
        self.name = name
        self.grade = grade
    
    def __str__(self):
        a = f"Student: {self.name} (grade: {self.grade})"
        return a

s1 = Student("Mimmo", 27)

def checkAttr(elem, attr):
    return hasattr(elem, attr)

print(checkAttr(s1, "name"))