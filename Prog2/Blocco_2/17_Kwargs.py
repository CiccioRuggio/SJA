import subprocess
subprocess.run("clear", shell=True)

def printStudent(**kwargs):
    for k, v in kwargs.items():
        print(f"{k}: {v}")

students = [
    {"name": "John Doe", "age": 23, "grade": 18},
    {"name": "John Doe", "age": 23, "grade": 18},
    {"name": "John Doe", "age": 23, "grade": 18}
    ]

for s in students:
    printStudent(**s)