#include <stdio.h>
#include <math.h>

int main(void) {

    int year;

    printf("Welcome to the leap year checker program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert a year: ");
    scanf("%d", &year);

    if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
        printf("The year %d is a leap year.\n", year);
    } else {
        printf("The year %d is not a leap year.\n", year);
    }
    return 0;
}