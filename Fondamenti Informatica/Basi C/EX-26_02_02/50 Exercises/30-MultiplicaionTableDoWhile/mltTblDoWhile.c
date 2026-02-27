// Stampare la tabellina di un numero usando do while .
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <math.h>

int main(void) {
    int num, i = 1;
    printf("Welcome to the multiplication table program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert a positive integer: ");
    scanf("%d", &num);
    printf("The multiplication table of %d is:\n|", num);

    do 
    {
        printf(" %d |", num * i);
        i++;
    } while(i <= 10); // Loop until we have printed the multiplication table up to 10

    printf("\n");
    return 0;
}