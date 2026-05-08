import subprocess
subprocess.run("clear", shell=True)

flag = False

username = input("Insert your username: ").strip()
password = input("Insert your password: ").strip()
if username == '' or len(password) < 6:
    flag = False
else:
    flag = True

if flag:
    print("Valid")
else:
    print("Invalid")