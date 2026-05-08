import subprocess
subprocess.run("clear", shell=True)

class Person:
    def __init__(self, name, age):
        self.name = name
        # se prima del nome di attr si mettono __ è come se stessimo mascherando láttributo, come il private. si definisce "Name Mangling"
        self.__age = age
    
    def getAge(self):
        return self.__age
        
p1 = Person("Marta", 19)

print(p1.name)
# per accedere a un attr private: {object}._{classname}__{privateattribute}
print(p1._Person__age)
print(p1.getAge())

# nel dict risulta?