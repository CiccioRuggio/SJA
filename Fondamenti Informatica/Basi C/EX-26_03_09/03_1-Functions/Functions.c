// Scrivere e usare le seguenti funzioni:
// int is_even(int x): ritorna 1 se pari, 0 altrimenti
// int abs_int(int x): valore assoluto
// int max2(int a, int b): massimo tra due interi
// leggere due interi da input e stampare:
// se ciascuno è pari o dispari
// il loro valore assoluto
// il massimo tra i due
// vincoli
// nessuna logica nel main oltre alle chiamate e alle printf
// usare correttamente i tipi di ritorno e i parametri

#include <string.h>
#include "../../Lib/mainFunc.h"

int validateIntInput()
{
    int num, validInput = 0;
    do
    {
        printf("Please insert a number: ");
        if (scanf("%d", &num) == 1)
        {
            validInput = 1;
        }
        else
        {
            //validInput = 0;
            printf("\n\n\nERROR:\n\tInvalid input. Please insert an integer.\n\n\n");
            while (getchar() != '\n' && getchar() != EOF); // flush leftover junk from buffer
        }
    } while (!validInput);

    return num;
}

int is_even(int x)
{
    if (x % 2 == 0)
    {
        return 1;
    }

    return 0;
}

int abs_int(int x)
{
    if (x < 0)
    {
        return -x;
    }

    return x;
}

int max2(int a, int b)
{
    if (a > b)
    {
        return a;
    }

    return b;
}

int main()
{
    system("clear");
    printWelcomeMessage("FUNCTIONS");

    int num1 = validateIntInput(), num2 = validateIntInput();

    if (is_even(num1))
    {
        printf("The number %d is even.\n", num1);
    }
    else
    {
        printf("The number %d is odd.\n", num1);
    }

    if (is_even(num2))
    {
        printf("The number %d is even.\n", num2);
    }
    else
    {
        printf("The number %d is odd.\n", num2);
    }

    printf("The absolute value of:\n%d\tis\t%d\n%d\tis\t%d\n", num1, abs_int(num1), num2, abs_int(num2));

    printf("The maximum between %d and %d is: %d\n", num1, num2, max2(num1, num2));

    return 0;

}