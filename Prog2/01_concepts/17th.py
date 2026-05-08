import subprocess
subprocess.run("clear", shell=True)

command = input("Insert a command: ")

match command:
    case "start":
        print("Program started!")
    case "stop":
        print("Program stopped")
    
    case "help":
        print("FOZZA CATANIA")
    
    case _:
        print("PALERMO MEDDA")