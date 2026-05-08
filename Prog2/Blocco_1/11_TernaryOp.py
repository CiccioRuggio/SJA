import subprocess
subprocess.run("clear", shell=True)

print("Even") if int(input("Insert a number: ")) % 2 == 0 else print("Odd")