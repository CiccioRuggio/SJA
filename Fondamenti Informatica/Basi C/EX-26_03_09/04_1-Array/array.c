#include <string.h>
#include "../../Lib/mainFunc.h"

void read_int_array(int array[], int size)
{
    for (int i = 0; i < size; i++)
    {
        array[i] = read_int_in_range("Insert a number", INT16_MIN, INT16_MAX);
    }

    printf("\t\tORIGINAL ARRAY\n");

    for (int i = 0; i < size; i++)
    {
        printf("| %d | ", array[i]);
    }

    printf("\n");
}

void swap_int_array(int array[], int size)
{
    for (int i = 0; i < size / 2; i++)
    {
        int temp = array[i];
        array[i] = array[size - 1 - i];
        array[size - 1 - i] = temp;
    }

    printf("\t\tREVERSED ARRAY\n");

    for (int i = 0; i < size; i++)
    {
        printf("| %d | ", array[i]);
    }

    printf("\n");
}

int main()
{
    system("clear");
    printWelcomeMessage("ARRAYS");

    int arraySize = read_int_in_range("Please insert the size of the array", 1, 30);
    int array[arraySize];

    read_int_array(array, arraySize);

    swap_int_array(array, arraySize);

    return 0;
}