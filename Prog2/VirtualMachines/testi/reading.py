import subprocess
subprocess.run("clear", shell=True)

path = "bravoStudente.txt"
path2 = "Prog2/VirtualMachines/testi/bravoStudente.txt"

with open(path2, "r") as f:
    content = f.read()
print(content)