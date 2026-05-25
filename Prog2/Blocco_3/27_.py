import subprocess
subprocess.run("clear", shell=True)

def evenList(l1):
    resList = []
    for el in l1:
        if el%2 ==0:
            resList.append(el)
    
    return resList

numList20 = [x for x in range(0,21)]

print(evenList(numList20))