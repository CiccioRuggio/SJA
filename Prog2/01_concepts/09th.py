import subprocess
subprocess.run("clear", shell=True)

a = int(input("Inserisci un'età"))

if a < 18:
    is_adult = False
else:
    is_adult = True

is_ad = False if a < 18 else True