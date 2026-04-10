a = 0

while a < 5:
    print(a)
    a += 1
    if a == 3:
        break #esce e conclude il ciclo in maniera NON naturale (uscendo quindi dalla condizione del while)
else: #l'else si attiva SOLO se il ciclo si conclude in maniera naturale
    print("Ciclo finito")