a = "Forza Catania!"

print(a[2:5]) #una string può essere LETTA come un iterabile, ma SOLO in lettura

#a[0] = "f" #l'assegnazione a indice x in una stringa non è possibile

b = "Catania" in a
print(b) #restituisce True perchè "Catania" E' presente nella stringa a

s = "           pyTHon       "

print(s)
print(s.capitalize()) #restituisce una stringa il cui PRIMO char è maiuscolo
print(s.upper()) #trasforma la stringa in MAIUSCOLO
print(s.replace("h", "j")) #sostituisce tutte le istanze di "h" con "j"
print(s.lower()) #trasforma la stringa in minuscolo
print(s.split(" ")) #divide la stringa a seconda del separatore (" ") e restituisce una lista di stringhe
print(s.strip()) #rimuove gli spazi iniziali e finali dalla stringa
print(s.join("-")) #aggiunge "-" tra ogni carattere della stringa s
print(s.find("p"))  #restituisce l'indice del primo carattere "p" nella stringa
print(s.count('t'))  #conta il numero di volte che compare la lettera 't' nella stringa s
print(len(s))
print(s) #stampa la stringa originale, i metodi precedenti NON effettuano modifiche sul dato, possono, eventualmente, portare a un nuovo dato con le caratteristiche derivanti dal metodo stesso

str1 = " hello WORld"

print(str1.strip().lower().capitalize())

s1 = "123abc"
s2 = "123abc-?"
s3 = "12354"
s4 = "ALPHA"

print( s1.isalnum(), s2.isalnum(), s3.isalnum(), s4.isalnum() )
print( s1.isdigit(), s2.isdigit(), s3.isdigit(), s4.isdigit() )
print( s1.islower(), s2.islower(), s3.islower(), s4.islower() )
print( s1.isupper(), s2.isupper(), s3.isupper(), s4.isupper() )
print( s1.isalpha(), s2.isalpha(), s3.isalpha(), s4.isalpha() )
