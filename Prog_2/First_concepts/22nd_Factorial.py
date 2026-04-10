def factorial(n):
    # base case
    if n == 1: #we avoid the calculation when 
        return 1
    else:
        return n*factorial(n-1)
    
print(factorial(5))