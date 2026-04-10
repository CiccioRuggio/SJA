str1 = input("Write something, don't bother about lower or uppercase:\n")
str2 = input("Write something AGAIN, don't bother about lower or uppercase:\n")

if str1.lower() == str2.lower():
    print("You inserted the same strings")
else:
    print("You inserted different strings")