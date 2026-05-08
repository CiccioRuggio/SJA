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

print(getattr(s1, "name")) # Mimmo
# print(getattr(s1, "age")) # AttributeError: 'Student' object has no attribute age
print(getattr(s1, "grade", None)) # 27 (default value)
print(getattr(s1, "address", "Not found")) # Not found (default value)