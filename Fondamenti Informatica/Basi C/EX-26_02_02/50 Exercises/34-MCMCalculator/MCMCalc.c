// Calcolare il minimo comune multiplo (mcm) di due numeri.
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <math.h>

int main(void) {
    int num1, num2, mcd, mcm;
    printf("Welcome to the MCM calculator program!\nPress ENTER to continue...");
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

    // Calculate the MCM using the relationship between MCD and MCM
    mcm = (num1 * num2) / mcd;

    printf("\nThe MCM of %d and %d is: %d\n", num1, num2, mcm);
    
    return 0;
}