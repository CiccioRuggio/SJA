a = 4
b = 5
c = a + b
print(c)

s1 = "Ciao "
s2 = "Mondo!"
print(s1+s2) #qui il sep NON è utilizzato
print(s1,s2) #qui il sep è utilizzato

PI = 3.14

#formatted string || fstring

nome = "Ciccio"

print("Ciao, mi chiamo", nome, "e amo l'aula F!")
print(f"Ciao, mi chiamo {nome} e amo l'aula F!")
print("Ciao, mi chiamo {nome} e amo l'aula F!")
print(f"Ciao, mi chiamo {input("Scrivi il tuo nome: ")} e amo l'aula F!")

name="Emanuele"
age = 52
is_funny = True

print(type(name), type(age), type(is_funny))
#return <class 'str'> <class 'int'> <class 'bool'>

#in python NON esiste nativamente il concetto di array, ma ci sono le list
#array => vector (array dinamici) => list
li = [1, 5.3, True, "Bambola", [1,2,3]]

print(li)
print(type(li))

li1 = [["Gianmarco", 27], ["Francesco", 27], ["Eros", 52]]
print(li1)
print(li1[0])
print(li1[0][0])

#tuple
t1 = (1,2,3, "Ciccio", False)
#differenza principale con le liste: tuple IMMUTABILI
# sono la cosa più vicina alle Costanti
# in generale è buona prassi che al termine di determinate operazioni l'output di una, esempio, funzione sia una tupla, proprio perchè immutabile
print(t1)
print(type(t1))

li2 = list(t1)
print(type(li2))