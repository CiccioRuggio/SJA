import subprocess
subprocess.run("clear", shell=True)

def sumArgs(*args):
    return sum(args)

def sumArgs2(*args):
    sum = 0
    for a in args:
        sum += a
    return sum

print(sumArgs(1,2,3,4,5))
print(sumArgs2(1,2,3,4,5))