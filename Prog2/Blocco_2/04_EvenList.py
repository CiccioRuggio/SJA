import subprocess
subprocess.run("clear", shell=True)

numList20 = [x for x in range(0,21)]

print(numList20)

listEven = [x for x in numList20 if x % 2 == 0]

print(listEven)
