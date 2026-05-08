import subprocess
subprocess.run("clear", shell=True)

numbers = [-3, -1, 0, 2, 5]

l1 = ["positive" if x >= 0 else "negative" for x in numbers]

print(l1)