import subprocess
subprocess.run("clear", shell=True)

class Student:
    description = "This class is supposed to be used anytime you will create a new Student"

    # def __METHOD__ sono metodi che si triggerano in condizioni particolari. quindi quando vengono definiti all'interno di una classe si determina il loro comportamento in tali condizioni
    # init è il costruttore, NON può essere vuoto, ha SEMPRE bisogno ALMENTO di self come parametro
    def __init__(self, name, surname, age=0, course=0):
        self.name = name.strip().capitalize()
        self.surname = surname.strip().capitalize()
        self.age = age
        self.course = course
        self.fullname = self.name + " " + self.surname
        
s1 = Student("tommaso", "pappalardo", 27, "Web Developer")

print(s1)
print(s1.name)
print(s1.surname)
print(s1.age)
print(s1.course)
print(s1.fullname)

s2 = Student("riccardino", "fuffolo")

print(s2)
print(s2.name)
print(s2.surname)
print(s2.age)
print(s2.course)
print(s2.fullname)