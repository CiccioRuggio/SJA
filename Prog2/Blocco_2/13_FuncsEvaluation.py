import subprocess
subprocess.run("clear", shell=True)

def evaluate(a):
    if isinstance(a,int):
        if a in range(18,31):
            return "Pass"
        else:
            return "Fail"

print(evaluate(int(input("Insert your grade: "))))