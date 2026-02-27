#include <stdio.h>
#include <math.h>
#include <unistd.h>

int main(void) {

    int a, b, c;
    printf("Welcome to the triangle checker program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert the lengths of the three sides of the triangle (separated by spaces): ");
    scanf("%d %d %d", &a, &b, &c);

    if (a <= 0 || b <= 0 || c <= 0) 
    {
        printf("\nThe lengths of the sides must be positive integers.\n");
    } 
    else if (a + b > c && a + c > b && b + c > a) 
    {
        printf("\nThe lengths %d, %d, and %d can form a triangle.\n", a, b, c);
    } 
    else
    {
        printf("\nThe lengths %d, %d, and %d cannot form a triangle.\n", a, b, c);
    }
}