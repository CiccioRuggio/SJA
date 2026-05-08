import subprocess
subprocess.run("clear", shell=True)

WEEKDAYS = ['monday', 'tuesday', 'wednesday', 'thursday']
WEEKEND = ['friday', 'saturday', 'sunday']

ipt = input("Insert a weekday: ").lower()

found = False

for el in WEEKDAYS:
    if ipt == el:
        print("It's a Weekday!")
        found = True
        break

for el in WEEKEND:
    if ipt == el:
        print("It's Weekend!")
        found = True
        break
if not found:
    print("You inserted a WRONG weekday.")