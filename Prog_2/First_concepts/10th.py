#fare inserire l'età all'utente: se è minorenne stampare "Accesso VIETATO", se è maggiorenne eseguire un altro controllo
#se età è minore di 35 stampare "Membro Junior";
#se età è compresa tra 35 e 50 "Membro Mid"
#se età è maggiore di 50 "Membro Senior"

a = int(input("Inserisci un'età:\n"))

if a < 18:
    is_adult = False
else:
    is_adult = True

if is_adult:
    if a < 35:
        print("Membro Junior")
    elif a <= 50:
        print("Membro Mid")
    else:
        print("Membro Senior")
else:
    print("Accesso VIETATO")