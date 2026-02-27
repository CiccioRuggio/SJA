// Leggere N numeri e contare quanti sono positivi, negativi e zero.
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <math.h>

int main(void) {
    int count, num, positiveCount = 0, negativeCount = 0, zeroCount = 0;
    printf("Welcome to the number counting program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert the number of integers you want to input: ");
    scanf("%d", &count);
    putchar('\n');

    for (int i = 0; i < count; i++) 
    {
        printf("Please insert integer #%d: ", i + 1);
        scanf("%d", &num);

        if (num > 0) 
        {
            positiveCount++; // Increment the count of positive numbers
        } 
        else if (num < 0) 
        {
            negativeCount++; // Increment the count of negative numbers
        } 
        else 
        {
            zeroCount++; // Increment the count of zeros
        }
    }

    printf("\nYou entered %d positive numbers, %d negative numbers, and %d zeros.\n", positiveCount, negativeCount, zeroCount);
    
    return 0;
}