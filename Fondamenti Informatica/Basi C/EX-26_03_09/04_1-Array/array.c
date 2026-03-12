#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void printWelcomeMessage(char programName[])
{
    printf("Welcome to the %s program!\nPress ENTER to continue...", programName);
    getchar();
    printf("\n\n\n");
}

int read_int_in_range(const char prompt[], int min, int max)
{
    int valid = 0, num;
    do
    {
        printf("%s (RANGE: %d - %d): ", prompt, min, max);

        if (scanf("%d", &num) == 1 && num >= min && num <= max)
        {
            valid = 1;
            while (getchar() != '\n')
                ; // clear buffer after good input too
        }
        else
        {
            printf("\n\n\nERROR:\n\tInvalid input. Please insert an integer.\n\n\n");
            while (getchar() != '\n')
                ; // flush leftover junk from buffer
        }
    } while (!valid);

    return num;
}

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