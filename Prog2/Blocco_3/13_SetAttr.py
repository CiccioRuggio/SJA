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

setattr(s1, "age", 30) # Aggiunge l'attributo age all'oggetto s1 con valore 30
print(s1.__dict__)