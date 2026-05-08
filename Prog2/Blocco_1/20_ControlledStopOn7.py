import subprocess
subprocess.run("clear", shell=True)

count = int(input("Insert a number from where to start (program will stop before a 7 mult): "))

while True:
    count += 1
    
    if count % 7 == 0:
        break
    
    print(count)
    
print("End")