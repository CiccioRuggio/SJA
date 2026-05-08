import subprocess
subprocess.run("clear", shell=True)

numbers = list(map(lambda x: x, range(1,101)))

squareEvenNumbers = list(filter(lambda x: x % 2 == 0, map(lambda y: y * y, numbers)))

print(squareEvenNumbers)