flag = False

correct = "abc123"
tentatives = 3

user = input("Enter your username:\n")

for i in range(1, tentatives + 1):
    guess = input("==========================\nEnter your password:\n")
    if guess.strip().lower() == correct:
        flag = True
        break
    else:
        print(f"\n\n==========================\nWrong Password, try again. Tentative: {i}")

print("==========================\nYou didn't find the correct password. Bye!") if not flag else print(f"==========================\nCongratulations {user}. You made it!")
