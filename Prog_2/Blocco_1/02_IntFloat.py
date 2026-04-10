x = int(input("Insert an integer number: "))
y = float(input("Insert a float number: "))

if x == y:
    print("They are the same numbers")
else:
    print("Numbers are not the same")

print(f"The type of {x} is: {type(x)};\nThe type of {y} is: {type(y)}")