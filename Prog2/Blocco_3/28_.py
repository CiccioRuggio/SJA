import subprocess
subprocess.run("clear", shell=True)

original = [3, 5, 2, 23, 4, 8]

def updateList(original):
    for el, i in enumerate(original):
        a = el
        original[i] = original[len(original) - i - 1]
        original[len(original) - i - 1] = a
    
    return original

print(updateList(original))