import subprocess
subprocess.run("clear", shell=True)

# while True:
#     try:
#         a = int(input("Enter a number: "))
#         b = 5 / a
#         print(b)
#     except:
#         ValueError(print("INVALID INPUT"))
#         continue
#     finally:
#         print("End of program")
#     break

try:
    a = int(input("Enter a number: "))
    b = 5 / a
    print(b)
# except ZeroDivisionError as e:
#     print(f"ERROR: {e}, type: {type(e)}")
# except (TypeError, IndexError) as e:
#     print(f"ERROR: {e}, type: {type(e)}")
except Exception as e:
    print(f"ERROR: {e}, type: {type(e)}")
    print(f"ERROR: {str(e)}, {repr(e)}")
    raise "Mbareeeeee a chi minchia fai, ah?"
# blocco else si attiva SOLO se il try va a buon fine, se parte l'except NON parte l'else
else:
    print("Good job! (siamo nell'else)")
# il finally, al contrario di else, si triggera SEMPRE, IN OGNI CASO, come chiusura della struttura dei try except
finally:
    print("End of program (siamo nel finally)")