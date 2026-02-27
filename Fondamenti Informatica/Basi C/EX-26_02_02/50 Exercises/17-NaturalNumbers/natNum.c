#include <stdio.h>
#include <math.h>
#include <unistd.h>

int main(void) {

    printf("Welcome to the natural numbers program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert a positive integer: ");
    int n, sum = 0;
    scanf("%d", &n);
    printf("\nThe first %d natural numbers are:\n|", n);

    for (int i = 1; i <= n; i++) 
    {
        printf(" %d |", i);
        sum += i;
    }
    
    printf("\nThe sum of the first %d natural numbers is: %d\n", n, sum);
    return 0;
}