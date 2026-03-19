#include <string.h>
#include "../../Lib/mainFunc.h"

void read_int_array(int arr[], int n)
{
    for (int i = 0; i < n; i++)
        arr[i] = read_int_in_range("Insert a number", INT16_MIN, INT16_MAX);
}

void print_array(const char prompt[], int arr[], int n)
{
    printf("%s: ", prompt);
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
    printWelcomeMessage("DUPLICATES REMOVAL");
    system("clear");

    int n = read_int_in_range("Enter the array size", 1, 50);
    int arr[n], compact[n];

    read_int_array(arr, n);
    print_array("Initial array", arr, n);

    int len = remove_duplicates(arr, n, compact);
    print_array("Array without duplicates", compact, len);

    printf("New length: %d\n", len);

    return 0;
}
