import subprocess
subprocess.run("clear", shell=True)

fruits = ["pear", "strawberry", "peach", "lemon", "apple"]

pFruits = [fruit for fruit in fruits if fruit.lower().rfind("p") != -1]

print(pFruits)