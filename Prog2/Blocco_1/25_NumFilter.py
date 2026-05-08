import subprocess
subprocess.run("clear", shell=True)

numList = [3, -4, 6, 8, -7, 12, 22, 10, -9, 2, 5, 0, -12, 7, -5, 11]

for num in numList:
    if num == 0:
        break
    elif num < 0:
        print("Lower then 0, skipping...")
        continue
    elif num > 10:
        print(num)
    else:
        print("Lower then 10, skipping...")
        
print("This is the end..")