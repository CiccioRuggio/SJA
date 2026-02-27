//Leggere numeri finché l’utente inserisce un numero pari.

#include <stdio.h>
#include <math.h>
#include <unistd.h>

int main(void) {
    int num;
    printf("Welcome to the even checker program!\nPress ENTER to continue...");
    getchar();

    do 
    {
        printf("\nPlease insert an odd number (or an even number to exit): ");
        scanf("%d", &num);

        if (num % 2 != 0) 
        {
            printf("\nYou entered an odd number: %d\n", num);
        } 
        else
        {
            printf("\nYou entered an even number: %d. Exiting the program.\n", num);
        }
    } while (num % 2 != 0); // Continue looping while the number is odd

    return 0;
}