#include <stdio.h>
#include <string.h>
#include <termios.h>
#include <unistd.h>

int main(void) {
    int a;
    int b;
    int sum;
    printf("Welcome to the addition program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert the first number: ");
    scanf("%d", &a);
    printf("Please insert the second number: ");
    scanf("%d", &b);
    sum = a + b;
    printf("The sum of %d and %d is %d\n", a, b, sum);
    return 0;
}