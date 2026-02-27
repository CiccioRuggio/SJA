#include <stdio.h>
#include <math.h>

int main(void) {
    float a, b, max;

    printf("Welcome to the maximum number finder program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert the first number: ");
    scanf("%f", &a);
    printf("Please insert the second number: ");
    scanf("%f", &b);

    max = a; // Assume a is the maximum initially
    if (b > max) {
        max = b; // Update max if b is greater. If it's equal, max remains a
    }

    printf("The maximum between %.2f and %.2f is: %.2f\n", a, b, max);
    
    return 0;
}