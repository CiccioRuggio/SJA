import subprocess
subprocess.run("clear", shell=True)

def printStudent(name, age, grade):
    print(f"Name: {name}\nAge: {age}\nGrade: {grade}")

printStudent("John Doe", grade = 23, age = 22)