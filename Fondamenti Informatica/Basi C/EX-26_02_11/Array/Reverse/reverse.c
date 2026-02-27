#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main(void)
{
    int size;
    printf("Welcome to the array program!\nPLEASE PRESS ENTER TO CONTINUE...\n");
    getchar();
    printf("Enter the size of the array: ");
    scanf("%d", &size);

    int array[size]; // Declare an array of the specified size.

    printf("\nNow please enter %d integers:\n", size);
    for (int i = 0; i < size; i++) {
        printf("Element #%d: ", i + 1);
        scanf("%d", &array[i]);
    }
    printf("\nYou entered the following array:\n");
    for (int i = 0; i < size; i++) {
        printf("%d ", array[i]);
    }

    printf("\nNow the array will be reverted:\n");
    for (int i = size - 1; i >= 0; i--) {
        printf("%d ", array[i]);
    }
    printf("\nProgram ended successfully.\n");
}