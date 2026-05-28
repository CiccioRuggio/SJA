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

text = input("Enter your text:\n")

words = len(text.split())
vocals = 0
consonants = 0
uppercase = 0
spaces= 0
specials = 0

for ch in text:
    if ch == ' ':
        spaces += 1
    elif ch.isalpha():
        if ch.lower() in 'aeiou':
            vocals += 1
        else:
            consonants += 1
        if ch.isupper():
            uppercase += 1
    elif not ch.isdigit():
        specials += 1

print(f"Words:      {words}")
print(f"Vocals:     {vocals}")
print(f"Consonants: {consonants}")
print(f"Uppercase:  {uppercase}")
print(f"Spaces:     {spaces}")
print(f"Specials:   {specials}")