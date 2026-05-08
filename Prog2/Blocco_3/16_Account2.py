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

acc1 = Account()
print("Saldo iniziale: ", acc1._Account__balance) # Saldo iniziale:  0
acc1.deposit(50)
print("Nuovo saldo: ", acc1._Account__balance)  # Nuovo saldo:  50
# print(acc1.__balance)  # AttributeError