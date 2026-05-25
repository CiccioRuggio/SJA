import subprocess
subprocess.run("clear", shell=True)

'''
1)
scrivi un "analizzatore di testo", ovvero un programma che,
data in input una stringa, calcoli:
-quante parole (separate da spazi) ci sono
-quante vocali sono presenti
-quante consonanti
-quante maiuscole
-quanti spazi
-quanti caratteri che non sono ne' lettere, ne' numeri , ne' spazi
'''

vocals = ['a','e','i','o','u']
specials = []
text = input("Enter your text:\n")
# words = 0
vocal_count = 0
consonant_count = 0

words = len(text.split())

for letter in text:
    if letter in vocals:
        vocal_count += 1

print(f"There are {words} words, {vocal_count} vocals")