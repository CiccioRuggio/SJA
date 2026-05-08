import subprocess
subprocess.run("clear", shell=True)

students = [
    {"name" : "John Doe", "age": 35, "grade": 27},
    {"name" : "Philip Turrets", "age": 55, "grade": 15},
    {"name" : "Massimo Boldi", "age": 67, "grade": 29},
    {"name" : "Donald J. Trump", "age": 154, "grade": 1},
]

for s in students:
    for k, v in s.items():
        if k == "age":
    
            print(v)