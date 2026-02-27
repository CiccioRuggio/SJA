// Stampare un triangolo rettangolo numerico crescente.
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <math.h>

int main(void)
{
    int height;
    printf("Welcome to the numeric triangle program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert the height of the triangle: ");
    scanf("%d", &height);

    printf("\nHere is your numeric triangle:\n");
    for (int i = 1; i <= height; i++)
    { // Loop to print each row of the triangle. If we initialize i to 1, we will print 1 number in the first row, 2 numbers in the second row, and so on, up to height numbers in the last row.
        for (int j = 1; j <= i; j++)
        { // Loop to print the numbers in each row. We use j <= i because we want to print numbers from 1 to i in the current row.
            printf("%d ", j); // Print the current number followed by a space for better formatting.
        }
        putchar('\n'); // Move to the next line after printing each row of numbers.
    }

    return 0;
}