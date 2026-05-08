import subprocess
subprocess.run("clear", shell=True)

class Student:
    description = "This class is supposed to be used anytime you will create a new Student"

    def __init__(self, name, surname, age=0, course=0):
        self.name = name.strip().capitalize()
        self.surname = surname.strip().capitalize()
        self.age = age
        self.course = course
        self.fullname = self.name + " " + self.surname
        
    # eq sta per equals. viene richiamato automaticamente quando si fa un confronto a partire da un oggetto della classe in cui viene definito
    def __eq__(self, other):
        if isinstance(other, Student):
            return self.name == other.name and self.surname == other.surname and self.age == other.age
        else:
            return False

s1 = Student("ciccio", "ruggio", 27, "Web Developer")
s2 = Student("ciccio", "ruggio", 13, "Web Developer")
s3 = Student("ciccio", "ruggio", 27, "Catechiesi della diocesi")

print(s1 == s2) # FALSE, age differente
print(s1 == s3) # TRUE, il criterio è rispettato e verificato