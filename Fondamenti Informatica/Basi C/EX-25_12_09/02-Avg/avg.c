#include <stdio.h>
#include <string.h>

int main(void) {
    // Queste variabili servono nel caso in cui si voglia calcolare la media
    // di un numero definito di valori inseriti dall'utente
    // int i;
    // int n;

    float number;
    float sum = 0.0;
    float average;

    printf("Welcome to the average calculation program!\nPlease press ENTER to continue...");
    getchar();

    // Qui si chiede all'utente di inserire il numero di valori da considerare
    // printf("Please insert the number of values to calculate average: ");
    // scanf("%d", &n);
 
    // for (i = 0; i < n; i++) {
        // printf("Please insert number %d): ", i + 1);
        // scanf("%f", &number);
        // sum += number;
    // }

    printf("Please insert the first number to calculate average: ");
    scanf("%f", &number);
    sum += number;

    printf("Please insert the second number to calculate average: ");
    scanf("%f", &number);
    sum += number;

    average = sum / 2;
    printf("The average of the entered numbers is %.2f\n", average);

    return 0;
}