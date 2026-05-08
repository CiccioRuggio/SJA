import subprocess
subprocess.run("clear", shell=True)


names = ["gigino", "paolino", "he bbbella hosa he hai detto"]

capNames = [name.capitalize() for name in names]

print(capNames)