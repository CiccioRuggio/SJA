import subprocess
subprocess.run("clear", shell=True)

def order(drink = "Water"):
    print(f"You ordered: {drink}")
    
order()
order("Rusty Nail")