import subprocess
subprocess.run("clear", shell=True)

original = [3, 5, 2, 23, 4, 8]

def updateList(original):
    for el in original:
        int(original[el]) = int(original[el]) + 1
    
    return original

print(updateList(original))