import subprocess
subprocess.run("clear", shell=True)

class Student():
    def __init__(self, name, age, grade):
        self.name = name
        self.age = age
        self.grade = grade
    def passed(self):
        return self.grade >= 60

s1 = Student("John Doe", 23, 12)
s2 = Student("Mirko Eros", 35, 60)
s3 = Student("Gabriele lenzo", 26, 30)
s4 = Student("Gigi Proiettile", 87, 90)
s5 = Student("Richard Mace", 24, 100)


students = [s1,s2,s3,s4,s5]

for s in students:
    if s.passed():
        print(s.name + '\n')