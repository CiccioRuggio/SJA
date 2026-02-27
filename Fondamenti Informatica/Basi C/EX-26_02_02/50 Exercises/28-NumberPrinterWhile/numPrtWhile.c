// Stampare i numeri da 1 a N usando while .
#include <stdio.h>
#include <math.h>
#include <unistd.h>
#include <stdlib.h>

int main(void) {
    int n, i = 1;
    printf("Welcome to the number printer program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert a positive integer: ");
    scanf("%d", &n);
    printf("The numbers from 1 to %d are:\n|", n);

    while(i <= n) 
    {
        printf(" %d |", i);
        i++;
    }
    printf("\n");
    return 0;
}