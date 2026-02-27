#include <stdio.h>
#include <math.h>
#include <unistd.h>

int main(void) {
    int count = 0;
    printf("Welcome to the multiple count program!\nPress ENTER to continue...");
    getchar();
    printf("\nThe multiples of 3 between 1 and 100 are:\n|");

    for (int i = 1; i <= 100; i++) 
    {
        if (i % 3 == 0) 
        {
            printf(" %d |", i);
            count++;
        }
    }
    
    printf("\nThe total count of multiples of 3 between 1 and 100 is: %d\n", count);
    return 0;
}