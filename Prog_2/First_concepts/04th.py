frutti = ["ananas", "banana", "cocco", "datteri", "eFrutto"]

print(frutti[1])
print(frutti[1:3]) #stampa da indice 1 a 2 (non incluso)
print(frutti[1:]) #stampa da indice 1 a fine
print(frutti[:3]) #stampa da indice 0 a 2 (non incluso)
print(frutti[-1]) #stampa l'ultimo elemento della lista

a = "ananas" in frutti #assegna True ad a perchè "ananas" E' nella lista frutti
b = "pesca" in frutti #assegna False a b perchè "pesca" NON è nella lista frutti

print(a, b)

frutti.append("fragola")
print(frutti)
print(len(frutti))

a = frutti.pop() #elimina l'ultimo elemento di una lista e lo restituisce (per questo è possibile assegnarlo a una variabile)
print(a)
print(frutti)

b = frutti.pop(2) #elimina l'elemento in indice 2 e lo restituisce (per questo è possibile assegnarlo a una variabile)
print(b)
print(frutti)

#frutti.clear() #elimina TUTTI gli elementi della lista

print(frutti)

sorted(frutti) #ritorna una lista ordinata, ma NON la modifica in origine

frutti.sort() #ordina la lista in ordine alfabetico

verdure = ["segale", "spinaci", "zucchine", "carote"]

print(frutti + verdure) #concatena le due liste, aggiungendo gli elementi della prima lista alla seconda, seguendo l'ordine di inserimento al momento della concatenazione

verdure.remove("segale") #rimuove il primo elemento uguale a "segale" nella lista
print(verdure)

verdure.insert(1, "prugne") #inserisce l'elemento "prugne" all'indice 1 (la seconda posizione), spostando gli altri element