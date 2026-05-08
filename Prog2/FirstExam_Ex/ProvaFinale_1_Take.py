import subprocess
subprocess.run("clear", shell=True)

class PriceError(Exception):
    def __init__(self, message = "INVALID PRICE ERROR"):
        super().__init__(message)

class Book:
    __price = 0
    def __init__(self, title, author, publisher, price = 0):
        self.title = title
        self.author = author
        self.publisher = publisher
        self.setPrice(price)
    
    def setPrice(self, price):
        if price < 0:
            self.__price = 0
            raise PriceError("Negative Prices NOT ALLOWED")
        else:
            self.__price = price
    def getPrice(self):
        return self.__price

class Catalog:
    __totValue = 0
    def __init__(self, name, descr, bookList, totValue = 0):
        self.name = name
        self.descr = descr
        self.bookList = bookList
        self.setTotValue()
    
    def setTotValue(self):
        self.__totValue = 0
        for el in self.bookList:
            if isinstance(el, Book) and el.getPrice() > 0:
                self.__totValue += el.getPrice()
    
    def getTotValue(self):
        self.setTotValue()
        return self.__totValue
    
    def __eq__(self, other):
        if isinstance(other, Catalog):
            return self.name == other.name and len(self.bookList) == len(other.bookList) and set(self.bookList) == set(other.bookList)
        else:
            raise Exception(f"Catalog: {other} is NOT a valid Catalog")

    def __add__(self, other):
        if isinstance(other, Catalog):
            return Catalog((self.name + " - " + other.name), (self.descr + " - " + other.descr), list(set(self.bookList + other.bookList)))
        else:
            raise Exception(f"Catalog {other.name} is NOT a valid Catalog")
    
    def addBook(self, bookToAdd):
        if isinstance(bookToAdd, Book) and bookToAdd not in self.bookList:
            self.bookList.append(bookToAdd)
            self.setTotValue()
            return True
        else:
            raise Exception(f"Book: {bookToAdd} is NOT a valid Book")
    
    def removeBook(self, bookToRemove):
        if isinstance(bookToRemove, Book) and bookToRemove in self.bookList:
            self.bookList.remove(bookToRemove)
            self.setTotValue()
            return True
        else:
            raise Exception(f"Book: {bookToRemove} is NOT a valid Book to remove")
        
b1 = Book("Pinocchio", "Mio Padre", "Mia Nonna", 12)
b2 = Book("Harry Potter", "J.K. Rowling", "Disney", 9)
b3 = Book("Il barone rampante", "Italo Calvino", "Casa Ponziat", 3)
b4 = Book("Topolino", "Giovanni Muchacha", "Disney", 5)
bList1 = [b1,b2,b3,b4]

cat1 = Catalog("Catalogo 1", "Questo è il primissimo catalogo, occhio", bList1)
cat2 = Catalog("Catalogo 2", "Questo è il secondissimo catalogo, piede", bList1)
cat3 = cat1 + cat2


print(f"{cat3.name} - {cat3.bookList}. \nPrezzo totale: {cat3.getTotValue()} Euro")
# print(f"{cat1.name} - {cat1.bookList}. \nPrezzo totale: {cat1.getTotValue()} Euro")
# print(f"{cat2.name} - {cat2.bookList}. \nPrezzo totale: {cat2.getTotValue()} Euro")


# a=['a','b','c']
# b=['d','f','c']

# c=list(set(a+b))
# c.sort()
# print(c) 