has_id = False


age = int(input("Insert your age: "))
if age >= 18:
    has_id = True if input("Do you have an ID? (y/n)").lower() == "y" else False
    if has_id:
        print("Access granted!")
else:
    print("Access denied")