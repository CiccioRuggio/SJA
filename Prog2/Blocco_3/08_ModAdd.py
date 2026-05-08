import subprocess
subprocess.run("clear", shell=True)

class Course:
    def __init__(self, students = []):
        self.students = students

    def __str__(self):
        a = f"Students: {self.students})"
        return a
    
    def __len__(self):
        if isinstance(self.students, list):
            return len(self.students)
        else:
            print("Not a list, can't give you length")
            return None
    
    def __add__(self, other):
        if isinstance(other, Course):
            return Course(self.students + other.students)
        else:
            print("Can't add a non-Course object")
            return None

c1 = Course(["Student 1", "Student 2", "Student 3"])
c2 = Course(["Student 5", "Student 6",  "Student 7"])
c3 = c1.__add__(c2)

print(c3)