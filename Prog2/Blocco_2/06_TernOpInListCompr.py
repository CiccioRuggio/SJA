import subprocess
subprocess.run("clear", shell=True)

l1 = ["EVEN" if x % 2 == 0 else "ODD" for x in range(0,10)]
print(l1)