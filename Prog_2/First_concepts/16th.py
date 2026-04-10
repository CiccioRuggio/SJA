s = "bella a tutti ragazzi, e benvenuti in questo nuovo video! Io sono CiccioGamer99."

s2 = s.split()

lw = [len(i) for i in s2]

cw = [i.capitalize() for i in s2]

ow =["P" if len(i) % 2 == 0 else "D" for i in s2 if i.isalpha()]
nw =["P" if len(i) % 2 == 0 else "D" for i in s2 if not i.isalpha()]

print(lw,cw,ow,nw)