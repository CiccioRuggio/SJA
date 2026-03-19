#ifndef MAIN_FUNC_H
#define MAIN_FUNC_H
#include <stdio.h>
#include <stdlib.h>

static void printWelcomeMessage(char programName[])
{
    system("clear");
    printf("Welcome to the %s program!\nPress ENTER to continue...", programName);
    getchar();
    printf("\n\n\n");
}

static int read_int_in_range(const char prompt[], int min, int max)
{
    int valid = 0, num;
    do
    {
        printf("%s (RANGE: %d - %d): ", prompt, min, max);

        if (scanf("%d", &num) == 1 && num >= min && num <= max)
        {
            valid = 1;
        }
        else
        {
            printf("\n\n\nERROR:\n\tInvalid input. Please insert an integer.\n\n\n");
            while (getchar() != '\n' && getchar() != EOF)
                ;
        }
    } while (!valid);

    return num;
}

static float add(float a, float b)
{
    return a + b;
}

static float diff(float a, float b)
{
    return a - b;
}

static float mult(float a, float b)
{
    return a * b;
}

static float divf(float a, float b)
{
    if (b != 0)
    {
        return a / b;
    }
    else
    {
        printf("ERROR: Division by zero is not allowed.\n");
        return 0;
    }
}
#endif