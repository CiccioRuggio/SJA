#include <stdio.h>
#include <math.h>

// int sum (int a, int b)
// {
//     return a + b;
// }

// // la seguente funzione è stata messa a solo scopo didattico, il printf fa esattamente la stessa cosa. qui si vuole evidenziare la differenza tra una funzione tipizzata (che quindi avrà un return) e una void (senza return)
// void printSmth(char str[])
// {
//     printf("%s", str);
// }

// void tryChange(int a)
// {
//     a = 12342;
// }

// int main(void)
// {

//     int value = 200;
//     tryChange(value);

//     // int num1 = 100, num2 = 20;
//     // int mySum = sum(num1, num2);
//     // printf("The sum of %d and %d is: %d\n", num1, num2, mySum);
// }

int main (void)
{
    int votes[] = {2, 3, 6, 7, 8, 5, 6, 10};

    for (int i = 0; i < sizeof(votes) / sizeof(votes[0]); i++) // sizeof(votes) restituisce la dimensione totale dell'array in byte, mentre sizeof(votes[0]) restituisce la dimensione di un singolo elemento dell'array (in questo caso, un int). Dividendo la dimensione totale per la dimensione di un elemento, otteniamo il numero di elementi nell'array.
    {
        printf("%d ", votes[i]);
    }
}