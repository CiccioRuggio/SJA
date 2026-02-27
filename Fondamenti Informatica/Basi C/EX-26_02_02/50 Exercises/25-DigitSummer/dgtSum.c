//Calcolare la somma delle cifre di un numero.

#include <stdio.h>
#include <math.h>
#include <unistd.h>

int main(void) {
    int num, sum = 0;
    printf("Welcome to the digit summer program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert a positive integer: ");
    scanf("%d", &num);

    while (num > 0) 
    {
        sum += num % 10; // Add the last digit to the sum
        num /= 10; // Remove the last digit
    }

    printf("\nThe sum of the digits is: %d\n", sum);
    return 0;
}