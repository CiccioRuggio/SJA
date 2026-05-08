import subprocess
subprocess.run("clear", shell=True)

def recurSum(n):
    if n <= 0:
        return 0
    else:
        return n + recurSum(n-1)
print(recurSum(5))