// Gestione input robusta tramite funzioni
// Implementare una funzione:
// int read_int_in_range(const char prompt[], int min, int max)
// che:

// stampa il prompt
// legge un intero
// se l’input non è numerico, ripulisce il buffer e ripete
// se il numero è fuori range, ripete
// restituisce il valore valido
// usare la funzione per leggere:
// N (1..20)
// N temperature (valori interi tra -50 e 50)
// poi implementare e usare anche:
// double average_from_sum(long sum, int n)
// che ritorna la media.
// stampare media, minimo e massimo delle temperature.
// vincoli
// la validazione deve stare dentro read_int_in_range
// main deve risultare “pulito” e corto

#include <string.h>
#include "../../Lib/mainFunc.h"

int read_iterations()
{
    return read_int_in_range("Please insert a number (this is how many temperatures you will be asked to insert)", 1, 20);
}

int read_temp()
{
    return read_int_in_range("Please insert a temperature", -50, 50);
}

float average_from_sum(float sum, int count)
{
    return sum / count;
}

int main()
{
    system("clear");
    printWelcomeMessage("ADVANCED FUNCTIONS");

    int iterations = read_iterations();
    int temperatures[iterations];
    float sum = 0.0;
    int min, max;

    for (int i = 0; i < iterations; i++)
    {
        temperatures[i] = read_temp();

        if (i == 0)
        {
            min = temperatures[i];
            max = temperatures[i];
        }

        if (temperatures[i] < min)
        {
            min = temperatures[i];
        }
        if (temperatures[i] > max)
        {
            max = temperatures[i];
        }

        sum += temperatures[i];
    }

    printf("\n\n\nThe average temperature is: %.2f\n", average_from_sum(sum, iterations));
    printf("The minimum temperature is: %d\n", min);
    printf("The maximum temperature is: %d\n", max);

    return 0;
}