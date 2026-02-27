#include <stdio.h>
#include <math.h>

int main(void) {

    int num;

    printf("Welcome to the integer checker program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert an integer number: ");
    scanf("%d", &num);

    if (num > 0) {
        printf("The number %d is positive.\n", num);
    } else if (num < 0) {
        printf("The number %d is negative.\n", num);
    } else {
        printf("The number is zero.\n");
    }

    return 0;

}