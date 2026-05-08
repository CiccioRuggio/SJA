import subprocess
subprocess.run("clear", shell=True)

class Student:
    #name ad esempio è un attributo di istanza, tendenzialmente ogni studente avrà un nome diverso
    name_pl = ""
    role_pl = "Student"
    #description è attributo di classe, non dovrebbe essere modificato dall'esterno
    description = "This class is supposed to be used anytime you will create a new Student"
    def wave(self):
        print(f"Ciao a tutti! Io sono {self.name}!")

s1 = Student()
s1.name = "Tommaso"

s2 = Student()
s2.name = "Lorenzo"

#in python è possibile creare un attributo sul momento a un oggetto
s1.age = 32

print(s1, s2)
print(s1.name, s2.name)

print(s1.age)

print(Student.description)
print(s1.description)

s1.wave()