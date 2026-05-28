import subprocess
subprocess.run("clear", shell=True)

'''
2)
il programma deve eseguire proceduralmente le seguenti operazioni:
chiedere di inserire in input una parola da indovinare, e verificare che questa parola sia una parola valida 
(3 inserimenti validi altrimenti si blocca) (esempio di parola: PALETTA)
Successivamente, stampare a video la seguente stringa:
"la parola da indovinare è *******", con un numero di asterischi corrispondente alle lettere effettive
della parola
L'utente ha a questo punto 3 tentativi di input per cercare delle lettere presenti nella parola.
Esempio:
Inserisci una lettera da cercare nella parola : A
Le lettere cercate finora sono: [A]
'''

word = ""
wrong_attempts = 3 # qui posso decidere a mio piacere quanti tentativi dare per indovinare la parola. specifico che sono tentativi d'errore, una volta quindi sbagliato questo numero di volte senza aver indovinato la parola il programma termina

for attempt in range(3):
    w = input("Enter the word to guess: ").strip().upper()
    if w.isalpha() and len(w) > 0:
        word = w
        break
    print(f"Invalid word. {3 - attempt} attempt(s) left.")

if not word:
    print("No valid word entered. Exiting.")
    exit()
subprocess.run("clear", shell=True) # per rendere tutto più 'realistico' una volta inserita la parola il terminale si cancella e parte con i guess



guessed = []

def display_word(word, guessed):
    return ' '.join(ch if ch in guessed else '*' for ch in word)

print(f"\nThe word to guess is: {display_word(word, guessed)}")



wrong = 0
won = False

while wrong < wrong_attempts and not won:
    letter = input("\nEnter a letter to search in the word: ").strip().upper()

    if not letter.isalpha() or len(letter) != 1:
        print("Please enter a single letter.")
        continue

    if letter not in guessed:
        guessed.append(letter)

    if letter in word:
        print(f"Yes! '{letter}' is in the word.")
    else:
        wrong += 1
        print(f"No, '{letter}' is not in the word. {wrong_attempts - wrong} wrong attempt(s) left.")

    print(f"Letters guessed so far: {guessed}")
    print(f"The word so far:        {display_word(word, guessed)}")

    # check if all letters of the word have been guessed
    found = 0
    for ch in word:
        if ch in guessed:
            found += 1
    if found == len(word):
        won = True

# print(f"\nYou used {wrong} wrong attempts!")
if won:
    print(f"You made it! You correctly guessed the word '{word}' in {wrong} wrong attempts!")
else:
    print(f"You lost, you used all {wrong_attempts} attempts and did not guess the word: {word}")
