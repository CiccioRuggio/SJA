// Calcolare la media di una serie di numeri inseriti dall’utente.
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <math.h>

int main(void)
{
    int count, num;
    double sum = 0.0, average;
    printf("Welcome to the average calculator program!\nPress ENTER to continue...\n");
    getchar();
    do
    {
        printf("Please insert the number of integers you want to input: ");
        scanf("%d", &count);
        if (count <= 0)
        {
            printf("\nPlease enter a POSITIVE integer for the count.\n");
        }
    } while (count <= 0); // This loop ensures that the user enters a valid positive integer for the count of numbers they want to input. If the user enters a non-positive integer, the program will prompt them to enter a valid positive integer until they do so.
    putchar('\n');

    for (int i = 0; i < count; i++)
    {
        printf("Please insert integer #%d: ", i + 1);
        scanf("%d", &num);
        sum += num; // Add the current number to the sum
    }

    average = sum / count; // Calculate the average by dividing the sum by the count of numbers
    printf("\nThe average of the numbers you entered is: %.2lf\n", average);

    return 0;
}