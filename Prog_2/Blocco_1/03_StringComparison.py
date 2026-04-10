str1 = input("Write the first string: ")
str2 = input("Write the second string: ")

if str1 < str2:
    print("The first string is alphabetically lower (in ASCII code)")
elif str1 > str2:
    print("The second string is alphabetically lower (in ASCII code)")
else:
    print("The strings are alpha-equal")
