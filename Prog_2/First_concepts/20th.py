# **ARGS and **KWARGS
def long(*numbers):
    print(len(numbers))

long(4,5,6,7,8,9)
long("ciao") # se nella def NON avessi messo * il risultato sarebbe stato 4 invece che 1

def mult(*numbers):
    tot = 1
    for n in numbers:
        tot *= n
    return tot

print(mult(2,3,4))

def team(**amesquattra):
    print("Roles:\n", amesquattra.keys())
    for role, name in amesquattra.items():
        print(f"{role}: {name}")
    
team(name = "C41", cap = "Gianmarco", coFounder = "Ciccio")