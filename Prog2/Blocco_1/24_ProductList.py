import subprocess
subprocess.run("clear", shell=True)

prodlist = ['apple', 'banana', 'raspberry']

for i, prod in enumerate(prodlist):
    print(f"{i+1} - {prod.capitalize()}")