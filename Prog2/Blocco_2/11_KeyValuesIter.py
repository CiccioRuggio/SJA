import subprocess
subprocess.run("clear", shell=True)

product = {"brand": "Apple", "model": "iPhone 13 Pro Max"}

for k, v in product.items():
    print(f"Key: {k}\nValue: {v}")