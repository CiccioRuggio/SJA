import subprocess
subprocess.run("clear", shell=True)

PSW = "python"

pswIpt = ""

while pswIpt.lower() != PSW.lower():
    pswIpt = input("Insert password: ")
print("Access granted!")