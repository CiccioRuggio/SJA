grade = int(input("Insert grade: "))

if grade >= 1 and grade < 60:
    print("Fail")
elif grade >= 60 and grade < 80:
    print("Passed")
elif grade >= 80 and grade <= 100:
    print("Excellent")
else:
    print("Invalid grade")