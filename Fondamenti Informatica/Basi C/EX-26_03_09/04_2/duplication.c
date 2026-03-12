#include <stdio.h>
#include <string.h>
#include <stdlib.h>

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
                ;
        }
        else
        {
            printf("\n\n\nERROR:\n\tInvalid input. Please insert an integer.\n\n\n");
            while (getchar() != '\n')
                ;
        }
    } while (!valid);

    return num;
}

void read_int_array(int arr[], int n)
{
    for (int i = 0; i < n; i++)
        arr[i] = read_int_in_range("Insert a number", -32768, 32767);
}

void print_array(const char label[], int arr[], int n)
{
    printf("%s: ", label);
    for (int i = 0; i < n; i++)
        printf("| %d ", arr[i]);
    printf("|\n");
}

int remove_duplicates(int arr[], int n, int compact[])
{
    int len = 0;
    for (int i = 0; i < n; i++)
    {
        int found = 0;
        for (int j = 0; j < len; j++)
        {
            if (compact[j] == arr[i])
            {
                found = 1;
                break;
            }
        }
        if (!found)
            compact[len++] = arr[i];
    }
    return len;
}

int main()
{
    system("clear");
    printWelcomeMessage("DUPLICATES REMOVAL");

    int n = read_int_in_range("Enter the array size", 1, 50);
    int arr[n], compact[n];

    read_int_array(arr, n);
    print_array("Initial array", arr, n);

    int len = remove_duplicates(arr, n, compact);
    print_array("Array without duplicates", compact, len);

    printf("New length: %d\n", len);

    return 0;
}
