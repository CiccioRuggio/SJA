import subprocess
subprocess.run("clear", shell=True)

cars = [
    {"brand": "Toyota", "year": 2020},
    {"brand": "Mazda", "year": 2017},
    {"brand": "Honda", "year": 2001},
       ]

for c in cars:
    for k, v in c.items():
        if k == "price":
            print(v)