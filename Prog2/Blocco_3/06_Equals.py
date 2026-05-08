import subprocess
subprocess.run("clear", shell=True)

class Student:
    def __init__(self, name, grade, age):
        self.name = name
        self.grade = grade
        self.age = age
    
    def __str__(self):
        a = f"Student: {self.name} (grade: {self.grade}, age: {self.age})"
        return a

    def __eq__(self, other):
        if isinstance(other, Student):
            return self.name == other.name and self.age == other.age
        else:
            return False

s1 = Student("Mimmo", 27, 25)
s2 = Student("Gianni", 30, 26)
s3 = Student("Mimmo", 30, 25)

print(s1)
print(s2)
print(s1 == s2)
print(s1 == s3)