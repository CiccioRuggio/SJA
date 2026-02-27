#include <stdio.h>
#include <math.h>
#include <unistd.h>

int main(void) {
    int n;

    printf("Welcome to the square printer program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert a positive integer: ");
    scanf("%d", &n);
    printf("\nThe squares of the first %d natural numbers are:\n|", n);

    for (int i = 1; i <= n; i++) 
    {
        printf(" %d |", i * i);
    }
}