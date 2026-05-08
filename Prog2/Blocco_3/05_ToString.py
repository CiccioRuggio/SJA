import subprocess
subprocess.run("clear", shell=True)

class Student:
    def __init__(self, name, grade):
        self.name = name
        self.grade = grade
        print(f"Student {self.name} created successfully with grade {self.grade}")
    
    def __str__(self):
        a = f"Student: {self.name} (grade: {self.grade})"
        return a

s1 = Student("Mimmo", 27)
s2 = Student("Gianni", 30)

print(s1)