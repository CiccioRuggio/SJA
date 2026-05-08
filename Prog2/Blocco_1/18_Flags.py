import subprocess
subprocess.run("clear", shell=True)

flag = False

while not flag:
    ipt = input("Write something ('stop' to end): ")
    if ipt.lower() == "stop":
        flag = True

print("Program stopped")