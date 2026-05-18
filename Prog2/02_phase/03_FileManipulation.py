import subprocess
subprocess.run("clear", shell=True)

path = "Prog2/02_phase/assets/text.txt"

'''
a= append   at= appendText  ab= appendByte
r= read     rt= readText    rb= readByte
w= write    wt= writeText   wb= writeByte
'''

#lettura e scrittura nei filw: r, w, a (reading, writing, append)
# file_stream = open(path, "r")
with open(path, "w") as fileWriter: #with open unisce open e close

    newText = '''
    nuovo testoooooooo
    fanculo flavio
    adesso sovrascrivo io'''
    fileWriter.write(newText) #scrivo nel file il nuovo testo
with open(path, "a") as fileAppend: #with open unisce open e close

    newText = '''
    appendoooooo'''
    fileAppend.write(newText) #scrivo nel file il nuovo testo
    
with open(path, "r") as fileReader: #leggo il file
    content = fileReader.readlines() #leggo tutte le righe

# file_stream.close()



print(content)