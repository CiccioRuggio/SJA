#include <stdio.h>
#include <math.h>
#include <unistd.h>

// Invertire un numero intero (es. 123 → 321).

int main(void) {
    int num, reversed = 0;
    printf("Welcome to the digit swapper program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert a positive integer: ");
    scanf("%d", &num);

    while (num > 0) 
    {
        int digit = num % 10; // Get the last digit
        reversed = reversed * 10 + digit; // Append the digit to the reversed number
        num /= 10; // Remove the last digit
    }

    printf("\nThe reversed number is: %d\n", reversed);
    return 0;
}