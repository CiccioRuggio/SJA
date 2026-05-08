import subprocess
subprocess.run("clear", shell=True)

class Course:
    def __init__(self, students = []):
        self.students = students

    def __len__(self, ):
        if isinstance(self.students, list):
            return len(self.students)
        else:
            print("Not a list, can't give you length")
            return None

c1 = Course(["Student 1", "Student 2", "Student 3"])

print(c1.__len__())