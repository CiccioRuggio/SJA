import subprocess
subprocess.run("clear", shell=True)

grades = [40,18,88,60,90,12,100,3]

def average(*numbers):
    return sum(numbers)/len(numbers)

passed = list(filter(lambda x: x >= 60, grades))

avgGeneral = average(*grades)
avgPassed = average(*passed)

print("Passed Grades: ", passed)
print("Average of all grades: ", avgGeneral)
print("Average of Passed grades: ", avgPassed)