fruits = ["Fragola", "Banana", "Cocco"]

#for l in list:
for fruit in fruits: #si accede direttamente all'elemento, tralasciando il classico controllo sull'indice
    print(fruit)
    
for i in range(len(fruits)): #qui si riesce a controllare l'indice (range di default con 1 solo parametro parte da 0 e arriva fino al valore inserito ESCLUSO)
    print(f"El. {i + 1}): {fruits[i]}")
    
for i in range(2,10): #inserendo 2 valori: il primo è il punto di partenza, il secondo di fine ESCLUSO
    print(i)
    
for i in range(0,51,5): #3 valori: inizio, fine, step (a step di 5 in questo caso)
    print(i)

for i, fruit in enumerate(fruits): #enumerate: restituisce indice e valore della lista (tupla di un solo elemento)
    print(f"El. {i+1}): {fruit}")
    
for i, fruit in enumerate(fruits, start=1): #start=1: imposta l'indice partendo da 1 (non da 0 come di default)
    print(f"El. {i}): {fruit}")