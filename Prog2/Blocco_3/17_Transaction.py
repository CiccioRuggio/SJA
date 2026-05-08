import subprocess
subprocess.run("clear", shell=True)

class Account:
    __balance = 0
    def __init__(self, balance = 0):
        self.__balance = balance  # Attribute name mangling (double underscore prefix)
    def deposit(self, amount):
        if amount > 0:
            self.__balance += amount
            self.__logTransaction()
            
    def getBalance(self):
        return self.__balance
    def __logTransaction(self):
        print(f"Transaction completed: new balance: {self.getBalance()}")
        # if amount > 0:
        #     self.__balance -= amount
        #     print(f"Transaction successfull, new balance: {self.getBalance()}")
        # else:
        #     print("Invalid transaction")


acc1 = Account()

acc1.deposit(20)