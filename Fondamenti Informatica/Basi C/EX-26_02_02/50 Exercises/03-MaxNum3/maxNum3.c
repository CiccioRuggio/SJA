#include <stdio.h>
#include <math.h>

int main(void) {

    int a, b, c, max;

    printf("Welcome to the maximum number finder program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert the first number: ");
    scanf("%d", &a);
    printf("Please insert the second number: ");
    scanf("%d", &b);
    printf("Please insert the third number: ");
    scanf("%d", &c);

    max = a; // Assume a is the maximum firstly

    if (b > max) {
        max = b; // Update max if b is greater. If it's equal, max remains a
    }

    if (c > max) {
        max = c; // Update max if c is greater. If it's equal, max remains unchanged
    }

    printf("The maximum between %d, %d, and %d is: %d\n", a, b, c, max);

    return 0;

}