// Calcolare il massimo comune divisore (MCD) di due numeri.
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <math.h>

int main(void) {
    int num1, num2, mcd;
    printf("Welcome to the MCD calculator program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert the first positive integer: ");
    scanf("%d", &num1);
    printf("Please insert the second positive integer: ");
    scanf("%d", &num2);

    // Calculate the MCD using the Euclidean algorithm
    int a = num1, b = num2;
    while (b != 0) 
    {
        int temp = b;
        b = a % b; // Update b to be the remainder of a divided by b
        a = temp; // Update a to be the previous value of b
    }
    mcd = a; // The last non-zero value of a is the MCD

    printf("\nThe MCD of %d and %d is: %d\n", num1, num2, mcd);
    
    return 0;
}