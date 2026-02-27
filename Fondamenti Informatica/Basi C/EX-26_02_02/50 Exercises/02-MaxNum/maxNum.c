#include <stdio.h>
#include <math.h>

int main(void) {

    int num1, num2, max;

    printf("Welcome to the max number finder program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert the first number: ");
    scanf("%d", &num1);
    printf("Please insert the second number: ");
    scanf("%d", &num2);

    max = num1;
    if (num2 > max) {
        max = num2;
    }

    printf("The maximum number between %d and %d is: %d\n", num1, num2, max);
    
    return 0;
}