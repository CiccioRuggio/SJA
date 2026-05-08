import subprocess
subprocess.run("clear", shell=True)

def factorial(n):
    # base case
    if n == 1: #we avoid the calculation when n is equal to 1
        return 1
    else:
        return n*factorial(n-1)
    
print(factorial(5))