a = 0
while a <= 10:
    if a % 2 != 0:
        a += 1
        continue
    print(a)
    a += 1
else:
    print("Ciclo finito")
