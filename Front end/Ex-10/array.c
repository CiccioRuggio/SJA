#include <stdio.h>


int main(void) {
    int array[] = {10, 20, 30, 40, 50};
    int size = sizeof(array) / sizeof(array[0]);

    for (int i = 0; i < size; i++) {
        printf("Element at index %d: %d\n", i, array[i]);
    }
}