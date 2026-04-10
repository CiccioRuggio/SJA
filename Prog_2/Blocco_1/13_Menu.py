menu = '''Select your task
1. Addition
2. Subtraction
3. Product
4. Division
0. Exit'''

choice = int(input(menu))

match choice:
    case 1:
        print("You selected ADDITION")
    case 2:
        print("You selected PRODUCT")
    case 3:
        print("You selected SUBTRACTION")
    case 4:
        print("You selected DIVISION")
    case 0:
        print("Exiting...")
    case _:
        print("Invalid option")
