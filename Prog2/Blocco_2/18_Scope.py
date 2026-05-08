import subprocess
subprocess.run("clear", shell=True)

a = 10
print(a)

def change():
    a = 6
    print(a)
change()
print(a)