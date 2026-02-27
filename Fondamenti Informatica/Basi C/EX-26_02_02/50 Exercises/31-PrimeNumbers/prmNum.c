// Stampare tutti i numeri primi da 1 a 100.
#include <stdio.h>
#include <math.h>
#include <unistd.h>

int main(void) {
    printf("Welcome to the prime number printer program!\nPress ENTER to continue...");
    getchar();
    printf("The prime numbers from 1 to 100 are:\n|");

    for (int num = 2; num <= 100; num++) 
    {
        int isPrime = 1; // Assume the number is prime until proven otherwise

        for (int divisor = 2; divisor <= sqrt(num); divisor++) 
        {
            if (num % divisor == 0) 
            {
                isPrime = 0; // The number is not prime
                break; // No need to check further divisors
            }
        }

        if (isPrime) 
        {
            printf(" %d |", num); // Print the prime number
        }
    }
    
    printf("\n");
    return 0;
}