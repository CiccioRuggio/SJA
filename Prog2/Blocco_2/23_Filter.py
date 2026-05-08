import subprocess
subprocess.run("clear", shell=True)

numbers = [1,3,33,2,43,20,11,-1,5,-7]
# filter(conditional function, iterable) => filter returns elements for which the function is True.

filtered = list(filter(lambda x: x > 10, numbers)) # x = input; x > 10 = condition
print(filtered) # [33, 43, 20, 11]
