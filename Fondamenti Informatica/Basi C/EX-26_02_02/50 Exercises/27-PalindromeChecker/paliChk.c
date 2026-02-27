// Verificare se un numero è palindromo.
#include <stdio.h>
#include <math.h>
#include <unistd.h>

int main(void) {
    int num, originalNum, reversed = 0;
    printf("Welcome to the palindrome checker program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert a positive integer: ");
    scanf("%d", &num);
    originalNum = num; // Store the original number for later comparison

    while (num > 0) 
    {
        int digit = num % 10; // Get the last digit
        reversed = reversed * 10 + digit; // Append the digit to the reversed number
        num /= 10; // Remove the last digit
    }

    if (originalNum == reversed) 
    {
        printf("\nThe number %d is a palindrome.\n", originalNum);
    }
    else
    {
        printf("\nThe number %d is not a palindrome.\n", originalNum);
    }
    
    return 0;
}