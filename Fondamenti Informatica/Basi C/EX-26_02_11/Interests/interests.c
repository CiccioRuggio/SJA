// Scrivere un programma che stampi una tabella che mostra gli interessi che derivano dalla somma di $100 investiti a vari tassi e per vari periodi. L'utente inserirà il tasso di interesse e il numero di anni. La tabella mostrerà il valore della somma iniziale dopo un numero di anni che varia da 1 al numero di anni inseriti e per vari tassi di interesse. Assumendo che la capitalizzazione avvegna ogni anno.

#include <stdio.h>
#include <math.h>

int main(void)
{
    double /*initial_amount = 100.0,*/ amount = 100.0, interest_rate;
    int years;

    printf("Welcome to the interest table program!\nPLEASE PRESS ENTER TO CONTINUE...\n");
    getchar();

    printf("Enter the annual interest rate (in percentage): ");
    scanf("%lf", &interest_rate);
    // interest_rate /= 100.0; // Convert percentage to decimal

    printf("Enter the number of years: ");
    scanf("%d", &years);
    double interestsArray[years]; // Assuming we want to store the amounts for 5 different interest rates
    double interestRates[years]; // Assuming we want to store the amounts for 5 different interest rates
    
    // Calculate the amount for each year and store it in the array
    for (int i = 0; i < years; i++)
    {
        if (i > 0) interest_rate += 0.5;            // Increment the interest rate for the next column
        interestRates[i] = interest_rate;           // Store the interest rate in the first column;
        amount *= 1 + interest_rate / 100.0;        // Calculate compound interest
        interestsArray[i] = amount;                 // Store the amount in the array
    }

    // for (int i = 0; i < years; i++)
    // {
    //     interestsArray[i] = interest_rate + i * 1.05; // Store the interest rate in the first column
    //     amount *= 1 + interestsArray[i] / 100.0;      // Calculate compound interest
    //     interestsArray[i] = amount;                   // Store the amount in the array
    // }

    // Print the table header
    printf("\n------------------------------------------------------------------------------------------------\n");
    printf("Year\t\t|");
    for (int j = 0; j < years; j++)
    {
        printf("\t%d", j + 1);
    }
    printf("\n------------------------------------------------------------------------------------------------\n");
    printf("Rate per Year\t|");

    for (int j = 0; j < years; j++)
    {
        printf("\t%.2lf%%", interestRates[j]);
    }
    printf("\n------------------------------------------------------------------------------------------------\n");
    printf("Capital\t\t|");
    for (int j = 0; j < years; j++)
    {
        printf("\t%.2lf", interestsArray[j]);
    }
    printf("\n------------------------------------------------------------------------------------------------\n");
    // for (int j = 0; j < years; j++)
    // {
    //     printf("\t%.2lf%%", interestsArray[j][0]);
    // }
    // printf("\n--------------------------------------------------------\n");
    // printf("Capital\t\t|");
    // for (int j = 1; j < years; j++)
    // {
    //     printf("\t%.2lf", interestsArray[0][j]);
    // }
    // printf("\n--------------------------------------------------------\n");

    // for (int i = 0; i < years; i++) {
    //     amount *= 1 + interest_rate;
    //     printf("After year %d, the amount is: %.2lf\n", i + 1, amount);
    //     interestsArray[i] = amount;
    // }

    // // printf("\nThe initial amount is: %.2lf\n", initial_amount);
    // printf("\nYear\t|\tAmount\n");
    // printf("----------------------\n");

    // for(int i = 0; i < years; i++) {
    //     printf("%d\t|\t%.2lf\n", i + 1, interestsArray[i]);
    // }

    // int array[2][3] = { {1, 2, 3}, {4, 5, 6} };

    // for (int i = 0; i < 2; i++) {
    //     for (int j = 0; j < 3; j++) {
    //         printf("%d ", array[i][j]);
    //     }
    //     printf("\n");
    // }
}