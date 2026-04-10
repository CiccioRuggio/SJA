a = []
for i in range(100):
    a.append(i*3)
print(a)

l = [i * 3 for i in range(100)] # List comprehension is faster and more readable than using append() method.
l1 = [i for i in range(20) if i % 2 == 0] # List comprehension with condition.

print(l1)