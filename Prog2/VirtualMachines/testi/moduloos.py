import subprocess, os
subprocess.run("clear", shell=True)

# print("Current folder", os.getcwd()) #cwd = current working directory
print("Current files list: ", os.listdir())

# os.mkdir("Nuova_Cartella") # mkdir = make directory

# os.rmdir("Nuova_Cartella")  # rmdir = remove directory

# os.chdir("Nuova_Cartella") # chd = change directory
print(f"Current folder: {os.getcwd()}")