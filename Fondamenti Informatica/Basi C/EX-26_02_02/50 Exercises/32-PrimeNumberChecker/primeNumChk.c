// Verificare se un numero è primo.
#include <stdio.h>
#include <math.h>
#include <unistd.h>

int main(void) {
    int num, isPrime = 1; // Assume the number is prime until proven otherwise
    printf("Welcome to the prime number checker program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert a positive integer: ");
    scanf("%d", &num);

    if (num <= 1) 
    {
        isPrime = 0; // Numbers less than or equal to 1 are not prime
    } 
    else 
    {
        for (int divisor = 2; divisor <= sqrt(num); divisor++) 
        {
            if (num % divisor == 0) 
            {
                isPrime = 0; // The number is not prime
                break; // No need to check further divisors
            }
        }
    }

    if (isPrime) 
    {
        printf("\nThe number %d is a prime number.\n", num);
    } 
    else 
    {
        printf("\nThe number %d is not a prime number.\n", num);
    }
    
    return 0;
}