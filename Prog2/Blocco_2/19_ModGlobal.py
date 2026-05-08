import subprocess
subprocess.run("clear", shell=True)

a = 10
print(a)

def change(a):
    a = 6
    print(a)
    return a
a = change(a)
print(a)