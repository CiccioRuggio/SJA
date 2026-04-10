a = 2
b = 3

print("2-3")
print(a == b)
print(a != b)
print(a > b)
print(a < b)
print(not a < b)

str1 = "A"
str2 = "B"
str3 = "a"
str4 = "b"

print("A-B-a-b")
print (str1 > str2) #ritorna FALSE perchè nella tabella ASCII A ha un valore di 65 e B ha un valore di 66
print (str3 > str4) #ritorna FALSE perchè nella tabella ASCII a ha un valore di 97 e b ha un valore di 98.
print(str1 > str3) #ritorna TRUE perchè nella tabella ASCII A ha un valore di 65 e a ha un valore di 97.
print (str2 > str4) #ritorna FALSE perchè nella tabella ASCII B ha un valore di 66 e b ha un valore di 98.

str5 = "Ordine"
str6 = "ordine"
str7 = "Oadine"
str8 = "oadine"

print("Ordine-ordine-Oadine-oadine")
print(str5 > str6) #ritorna FALSE perchè Python confronta le stringhe letter per letter
print(str7 > str8)
print(str5 > str7)
print(str6 > str8) #ritorna TRUE perchè nella tabella ASCII la minuscola è maggiore della maiuscola.