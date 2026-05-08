import subprocess
subprocess.run("clear", shell=True)

count = 0

while count <= 20:
    count += 1
    if count % 3 == 0:
        continue
    print(count)
    