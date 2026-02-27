// Leggere una sequenza di numeri e stampare il massimo e il minimo.
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <math.h>

int main(void) {
    int count, num, max, min;
    printf("Welcome to the max and min finder program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert the number of integers you want to input: ");
    scanf("%d", &count);
    putchar('\n');

    while (count <= 0) 
    {
        printf("Please enter a POSITIVE integer for the count: ");
        scanf("%d", &count);
        // This loop ensures that the user enters a valid positive integer for the count of numbers they want to input. If the user enters a non-positive integer, the program will prompt them to enter a valid positive integer until they do so.
    }

    printf("\nPlease insert integer #1: ");
    scanf("%d", &num); // We don't care whether the first number is neg, pos or 0
    // VERY USEFUL WAY TO INITIALIZE MORE THAN ONE VAR TO THE SAME VALUE
    max = min = num; // Initialize max and min to the first number

    for (int i = 1; i < count; i++) 
    {
        printf("Please insert integer #%d: ", i + 1);
        scanf("%d", &num);

        if (num > max) 
        {
            max = num; // Update max if the current number is greater
        } 
        else if (num < min) 
        {
            min = num; // Update min if the current number is smaller
        }
    }

    printf("\nThe maximum number you entered is: %d\n", max);
    printf("The minimum number you entered is: %d\n", min);
    
    return 0;
}