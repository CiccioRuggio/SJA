#include <math.h>
#include <stdbool.h>
#include "../../Lib/mainFunc.h"

// se cliente premium e importo > 100 sconto 15%
// se importo > 200 sconto 10%
// se importo > 100 sconto 5%
// se coupon valido sconto += 5% ma non può essere > 20%
// se prodotto escluso > 0, sconto = 0
// se premium e importo > 150 sconto 10€
// se importo finale < 0 stampa 0

int main()
{
    system("clear");
    printWelcomeMessage("DISCOUNTS");

    float amount = 0.0;
    float discount = 0.0;
    bool premium = false;
    bool validCoupon = false;
    bool excludedProduct = false;

    printf("\n\nInsert the amount to pay: ");
    scanf("%f", &amount);

    char premiumInput;
    printf("\nAre you a premium customer? (y/n): ");
    scanf(" %c", &premiumInput);
    if (premiumInput == 'y' || premiumInput == 'Y')
    {
        premium = true;
    }

    char couponInput;
    printf("\nDo you have a valid coupon? (y/n): ");
    scanf(" %c", &couponInput);
    if (couponInput == 'y' || couponInput == 'Y')
    {
        validCoupon = true;
    }

    char exclProdInput;
    printf("\nDo you have an excluded product? (y/n): ");
    scanf(" %c", &exclProdInput);
    if (exclProdInput == 'y' || exclProdInput == 'Y')
    {
        excludedProduct = true;
    }

    printf("\nInitial amount: €%.2f\n", amount);

    if (excludedProduct)
    {
        discount = 0;
    }
    else
    {
        if (premium && amount > 150)
        {
            amount -= 10;
        }
        if (premium && amount > 100)
        {
            discount += 0.15;
        }
        else if (amount > 200)
        {
            discount += 0.10;
        }
        else if (amount > 100)
        {
            discount += 0.05;
        }
        if (validCoupon)
        {
            discount += 0.05;
        }
        if (discount > 0.20)
        {
            discount = 0.20;
        }
    }

    printf("\nTotal discount: %.2f%%\n", discount * 100);
    amount -= amount * discount;
    if (amount < 0)
    {
        amount = 0;
    }
    printf("\nFinal amount to pay: €%.2f\n", amount);
}