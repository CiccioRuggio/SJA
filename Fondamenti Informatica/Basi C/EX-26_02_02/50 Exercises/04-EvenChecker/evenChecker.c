#include <stdio.h>
#include <math.h>

int main(void) {

    int num;

    printf("Welcome to the even checker program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert an integer number: ");
    scanf("%d", &num);

    if (num % 2 == 0 && num != 0) {
        printf("The number %d is even.\n", num);
    }
    else if (num == 0) {
        printf("The number is zero, which is considered even.\n");
    }
    else {
        printf("The number %d is odd.\n", num);
    }

    return 0;
}