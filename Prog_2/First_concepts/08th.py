#truthy e falsy values
print(bool(1)) #True
print(bool(-1)) #True
print(bool(0)) #False
print(bool('a')) #True
print(bool(' ')) #True
print(bool('')) #False
print(bool([])) #False
print(bool({})) #False
#None è un valore falsy
print(bool(None)) #False
#NULL è anche un valore falsy.
print(bool()) #False

if 3 < 5:
    print("Sei bella")
else:
    print("Sei fotomodella")
    
str1 = input("Che pensi?")
if str1:
    print(f"Hai inserito il messaggio:\n{str1}")
else:
    print("Messaggio NON valido")

