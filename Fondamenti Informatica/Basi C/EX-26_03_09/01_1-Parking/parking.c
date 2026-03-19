#include <math.h>
#include <stdbool.h>
#include "../../Lib/mainFunc.h"

int main() {
    system("clear");
    printWelcomeMessage("Parking");
    char pricePrint[] = "The price for your stay is: €";

    //30- min = 1€
    //31-120 min = 3€
    //120+ min = 3€ + 1€ ogni 60 min oltre i 120

    int minutes;
    float price = 0;
    printf("Insert the number of minutes of your stay: ");
    scanf("%d", &minutes);

    if (minutes < 0)
    {
        printf("Invalid input. Please insert a positive number of minutes.\n");
    }
    else if (minutes > 0 && minutes <= 30)
    {
        price = 1;
    }
    else if (minutes > 30 && minutes <= 120)
    {
        price = 3;
    }
    else if (minutes > 120)
    {
        //price = 3 + ((minutes - 120) / 60.0);
        if (minutes - 120 <= 60)
        {
            price = 4;
        }
        else if (minutes - 120 > 60 && (minutes - 120) % 60 == 0)
        {
            price = 3 + (minutes - 120) / 60;
        }
        else
        {
            price = 3 + (minutes - 120) / 60 + 1;
        }
    }
    printf("%s%.2f\n", pricePrint, price);
}