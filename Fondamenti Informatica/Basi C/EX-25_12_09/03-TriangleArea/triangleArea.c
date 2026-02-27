#include <stdio.h>
#include <math.h>

int main(void) {
    float base, height, area;

    printf("Welcome to the triangle area calculation program!\nPlease press ENTER to continue...");
    getchar();
    printf("Please insert the base of the triangle: ");
    scanf("%f", &base);
    printf("Please insert the height of the triangle: ");
    scanf("%f", &height);

    area = (base * height) / 2;
    printf("The area of the triangle with base %.2f and height %.2f is %.2f\n", base, height, area);

    return 0;
}