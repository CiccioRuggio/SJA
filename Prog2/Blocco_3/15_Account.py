import subprocess
subprocess.run("clear", shell=True)

class Account:
    __balance = 0
    def __init__(self, balance = 0):
        self.__balance = balance  # Attribute name mangling (double underscore prefix)
    def deposit(self, amount):
        if amount > 0:
            self.__balance += amount
            
    def getBalance(self):
        return self.__balance
    