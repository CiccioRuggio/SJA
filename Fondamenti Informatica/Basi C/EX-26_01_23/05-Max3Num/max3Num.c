#include <stdio.h>
#include <math.h>

int main(void) {

    float a, b, c, max;

    printf("Welcome to the maximum number finder program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert the first number: ");
    scanf("%f", &a);
    printf("Please insert the second number: ");
    scanf("%f", &b);
    printf("Please insert the third number: ");
    scanf("%f", &c);

    max = a; // Assume a is the maximum initially

    if (b > max) {
        max = b; // Update max if b is greater. If it's equal, max remains a
    }

    if (c > max) {
        max = c; // Update max if c is greater. If it's equal, max remains unchanged
    }

    printf("The maximum between %.2f, %.2f, and %.2f is: %.2f\n", a, b, c, max);

    return 0;

    // if (a > b && a > c) {
    //     max = a;
    // } else if (b > a && b > c) {
    //     max = b;
    // } else {
    //     max = c;
    // }

}