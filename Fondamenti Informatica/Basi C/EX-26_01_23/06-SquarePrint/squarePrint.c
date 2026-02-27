#include <stdio.h>
#include <math.h>

int main(void) {

    int n, i = 1, square;

    printf("Welcome to the square printer program!\nPress ENTER to continue...");

    getchar();

    printf("Please insert a positive integer number, to know how many line the program will print: ");

    scanf("%d", &n);

    printf("Calculating the squares from %d to %d:\n", i, n);

    printf("-------------------------------------------\nNUMBER:  SQUARE:\n");
    
    while (i <= n)
    {
        square = i * i;
        //printf("The square of %d is: %d\n", i, square);
        if(square < 10 && i < 10)
            printf("|  %d    |  %d    |\n", i, square);
        else if(square < 100 && i < 10)
            printf("|  %d    |  %d   |\n", i, square);
        else if(square >= 100 && i >= 10)
            printf("|  %d   |  %d  |\n", i, square);
        else
        printf("|  %d    |  %d  |\n", i, square);
        i++;
    }

    // for (int i = 1; i <= n; i++)
    // {
    //     square = i * i;
    //     //printf("The square of %d is: %d\n", i, square);
    //     if(square < 10 && i < 10)
    //         printf("|  %d    |  %d    |\n", i, square);
    //     else if(square < 100 && i < 10)
    //         printf("|  %d    |  %d   |\n", i, square);
    //     else if(square >= 100 && i >= 10)
    //         printf("|  %d   |  %d  |\n", i, square);
    //     else
    //     printf("|  %d   |  %d  |\n", i, square);
    // }
    

    return 0;

}