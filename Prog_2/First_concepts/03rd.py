a = 5
b = a
b = 6
print(a, b)

c = ["Anna", "Carlo"]
d = c # IMPORTANTE qui non si fa un'assegnazione a valore, ma di INDIRIZZO DI MEMORIA. Quindi se cambio d nel futuro anche c verrà modificato perchè puntano allo stesso indirizzo di memoria, quindi sono effettivamente UGUALI
e = c.copy()
d[0] = "Pippo"

print(c)
print(d)
print(e)