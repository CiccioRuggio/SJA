import subprocess
subprocess.run("clear", shell=True)

numList = []

for x in range(0,10):
    numList.append(x)
    
squareList = [x*x for x in numList]

print(squareList)