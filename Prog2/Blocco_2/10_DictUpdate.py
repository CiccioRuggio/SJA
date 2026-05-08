import subprocess
subprocess.run("clear", shell=True)

product = {"brand": "Apple", "model": "iPhone 13 Pro Max"}

product.update({"brand": "Apple INC", "price": "1699"})

print(product)