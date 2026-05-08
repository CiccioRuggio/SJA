import subprocess
subprocess.run("clear", shell=True)

words = ["python", "code", "list", "comprehension"]

lengthList = [len(word) for word in words]

print(lengthList)