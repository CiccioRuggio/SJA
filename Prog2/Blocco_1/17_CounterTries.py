import subprocess
subprocess.run("clear", shell=True)

counter = 0
ipt = 1

while ipt != 0:
    ipt = int(input("Insert a number (0 to end): "))
    if ipt != 0 and ipt > 0:
        counter += 1
print(f'You inserted {counter} positive numbers')