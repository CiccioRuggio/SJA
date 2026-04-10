a = int(input("Insert the first number: "))
b = int(input("Insert the second number: "))

if a < b:
    print(f"{a} is lower then {b}")
elif a > b:
    print(f"{a} is higher than {b}")
else:
    print("The numbers are equal")