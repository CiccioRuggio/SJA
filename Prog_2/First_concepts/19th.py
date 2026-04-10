def wave(user):
    print(f"Hello {user["name"]}")
    
users = [
    {
        "name" : "Gianmarco",
        "age" : 27,
        "gender" : "M"
    },
    {
        "name": "Ciccio",
        "age" : 27,
        "gender" : "M"
    }
]

for u in users:
    wave(u)
    
def mult(a, b = "FOZZA CATANIA", c = 4):
    if a == 3:
        return 3
    else:
        print(a * b * c)

mult(5,6,2)
mult(5,6)
mult("Pipo", 3)
mult(2, "Papo")
#mult("Pipo", "Papo") # returns error since u cant mult 2 strings
mult(3)

mult(2,3,4)
mult(b = 2, c = 3, a = 1) # keyword arguments
#mult(a=1, b=2, c=3, d=4) # error since e is not defined in function mult()
mult(5, c = 4, b = 2) # in mixed def the keyword args MUST be at the end, otherwise => error
