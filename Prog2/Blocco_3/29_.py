import subprocess
subprocess.run("clear", shell=True)

listToCheck = [x for x in range(1,21)]

def proced(l1):
    ret = []
    for el in l1:
        if el > 10:
            ret.append(el)
    
    return ret

class Checker:
    def __init__(self, l1):
        self.l1 = l1
        self.l2 = []
        
    def defineL2(self):
        for el in self.l1:
            if el > 10:
                self.l2.append(el)

# print(proced(listToCheck))

c1 = Checker(listToCheck)
c1.defineL2()

print(proced(c1.l1))
print(c1.l2)
