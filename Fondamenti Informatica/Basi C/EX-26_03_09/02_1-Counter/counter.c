#include <math.h>
#include <stdbool.h>
#include "../../Lib/mainFunc.h"

// leggere da input n numeri, terminare a 0.
// contare i numeri positivi e i negativi e quanti numeri sono stati inseriti in totale (0 escluso)
// calcolare la somma di tutti i num

int main ()
{
    system("clear");
    printWelcomeMessage("COUNTER");
    int num, countPos = 0, countneg = 0;
    float sum = 0;
    
    do
    {
        printf("Insert a number (0 stops the program): ");
        scanf("%d", &num);
        if (num > 0)
        {
            countPos++;
        }
        else if (num < 0)
        {
            countneg++;
        }
        sum += num;
    } while (num != 0);

    if (num == 0)
    {
        printf("\nYou entered %d, the program will stop.\n", num);
    }

    printf("You entered %d numbers, %d positive and %d negative.\n", countPos + countneg, countPos, countneg);
    printf("The sum of all the numbers you entered is: %.2f\n", sum);
    
}