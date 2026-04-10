d = {
    "name": "Gianmarco",
    "age": 27,
    "gender": "M"
    }
print(f"Hello {d['name']}, your age is {d['age']}")

ipt = input("Insert what you want to know about this user: ")

print(d.get(ipt, "NOT EXISTING KEY")) # if key not exist return second argument (default None)

if not "tifo" in d:
    d["tifo"] = "FOZZA CATANIA"

print(d["tifo"])

print(sorted(d)) # returns a list of keys sorted alphabetically

for k, v in d.items():
    print(k, v)

# for v in d.values():
#     print(k, d[k])a
