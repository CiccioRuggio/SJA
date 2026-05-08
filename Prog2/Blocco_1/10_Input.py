import subprocess
subprocess.run("clear", shell=True)

num = int(input("Insert a number: "))

if num < 0:
    print("Negative")
elif num == 0:
    print("Zero")
else:
    print("Positive")