import subprocess
subprocess.run("clear", shell=True)

count = 1
sum = 0

while count <= 100:
    sum += count
    count += 1

print(sum)