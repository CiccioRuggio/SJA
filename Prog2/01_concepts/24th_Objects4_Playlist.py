import subprocess
subprocess.run("clear", shell=True)

class Playlist:
    desc = "Una playlist è una raccolta di canzoni o video musicali"
    
    def __init__(self, name, pl_desc, songs=[]):
        self.name = name
        self.pl_desc = pl_desc
        self.songs = songs
    
    def __add__(self, other):
        if isinstance(other, Playlist):
            newName = self.name + " - " + other.name
            newPl_desc = "Playlist 1:\n" + self.pl_desc + "\nPlaylist 2:\n" + other.pl_desc
            newSongs = self.songs + other.songs
            return Playlist(newName, newPl_desc, newSongs)
        else:
            print("'Nse po fa bro")
    
    def __str__(self):
        a = f'''Titolo della playlist: {self.name}
{self.pl_desc}
Questa playlist contiene {len(self.songs)} canzoni
{self.songs}
        '''
        return a
    
pl1 = Playlist("estate 2k25", "Le hit estive di quando ho conosciuto Giuliana", ["Bella Ciao", "Estate", "Non mi piacerebbe"])
pl2 = Playlist("estate 2k23", "Le hit estive di quando ho conosciuto Cristina", ["Money Trees", "Estate"])

print(pl1.name)
print(pl1.songs)
print(pl2.name)
print(pl2.songs)

pl3 = pl1 + pl2
print(pl3.name)
print(pl3.songs)
print(pl3.pl_desc)

print(pl3)

print(f"Has name?", hasattr(pl1, "name"))
print(f"Has promotion?", hasattr(pl1, "promotion"))
print('name' in list(pl1.__dict__))

print(pl1.name) # HARDCODED
a = input("Insert the attribute to print: ")
if hasattr(pl1, a):
    print(getattr(pl1, a, "ATTRIBUTE NOT FOUND")) # SOFTCODED
else:
    b = input(f"Attribute not found, insert the value to assign to new attribute ({a}): ")
    setattr(pl1, a, b)

print(getattr(pl1,a))