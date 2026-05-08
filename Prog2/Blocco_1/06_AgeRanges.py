import subprocess
subprocess.run("clear", shell=True)

a = int(input("Insert your age:\n"))

if a < 14:
    print("You are a child")
elif a < 17:
    print("You are a teenager")
else:
    print("You are an adult")
    