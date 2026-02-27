// Stampare un triangolo di asterischi di altezza N.
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <math.h>

int main(void)
{
    int height;
    printf("Welcome to the star triangle program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert the height of the triangle: ");
    scanf("%d", &height);

    printf("\nHere is your star triangle:\n");
    for (int i = 1; i <= height; i++)
    { // Loop to print each row of the triangle. If we initialize i to 1, we will print 1 star in the first row, 2 stars in the second row, and so on, up to height stars in the last row.
        for (int j = 0; j < i; j++)
        { // Loop to print the stars in each row. We use j < i because we want to print i stars in the current row.
            putchar('*'); // Print a star character for each iteration of the inner loop, which creates the desired number of stars for that row.
        }
        putchar('\n');
    }

    // Here's an alternative way to print a centered triangle, which is more visually appealing. It calculates the number of spaces and stars needed for each row to create a centered triangle shape.
    // for (int i = 1; i <= height; i++)
    // {
        // int spaces = height - i; // Calculate the number of spaces needed to center the stars. The number of spaces is equal to the height of the triangle minus the current row number (i). This ensures that as we move down the rows, we decrease the number of spaces, creating a centered effect.
        // int stars = 2 * i - 1; // Calculate the number of stars needed for the current row. The number of stars follows the pattern of odd numbers (1, 3, 5, etc.), which can be calculated using the formula 2 * i - 1, where i is the current row number. This ensures that each row has an odd number of stars, creating a visually appealing triangle shape.
        // 
        // for (int s = 0; s < spaces; s++)
        // {
            // putchar(' '); // Print the spaces before the stars. We use putchar to print a single space character for each iteration of the loop, which helps to center the stars in the triangle.
        // }
        // for (int s = 0; s < stars; s++)
        // {
            // putchar('*'); // Print the stars for the current row. We use putchar to print a single star character for each iteration of the loop, which creates the desired number of stars for that row.
        // }
        // putchar('\n');
    // }

    return 0;
}