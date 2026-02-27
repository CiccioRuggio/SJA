// Stampare una piramide di numeri.
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <math.h>

int main(void)
{
    int height;
    printf("Welcome to the number pyramid program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert the height of the pyramid: ");
    scanf("%d", &height);

    printf("\nHere is your number pyramid:\n");
    for (int i = 1; i <= height; i++)
    {
        int spaces = height - i; // Calculate the number of spaces needed to center the numbers. The number of spaces is equal to the height of the triangle minus the current row number (i). This ensures that as we move down the rows, we decrease the number of spaces, creating a centered effect.
        int numbers = 2 * i - 1; // Calculate the number of numbers needed for the current row. The number of numbers follows the pattern of odd numbers (1, 3, 5, etc.), which can be calculated using the formula 2 * i - 1, where i is the current row number. This ensures that each row has an odd number of numbers, creating a visually appealing triangle shape.

        for (int s = 0; s < spaces; s++)
        {
            putchar(' '); // Print the spaces before the numbers. We use putchar to print a single space character for each iteration of the loop, which helps to center the numbers in the triangle.
        }
        for (int s = 0; s < numbers; s++)
        {
            putchar('0' + (s % 10)); // Print the numbers for the current row. We use putchar to print a single number character for each iteration of the loop, which creates the desired number of numbers for that row.
        }
        putchar('\n');
    }

    return 0;
}