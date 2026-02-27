#include <stdio.h>
#include <math.h>

int main(void) {

    int age;

    printf("Welcome to the age checker program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert your age: ");
    scanf("%d", &age);

    if (age >= 18 && age < 65) {
        printf("You are an adult.\n");
    } else if (age >= 65) {
        printf("You are a senior citizen.\n");
    } else {
        printf("You are a minor.\n");
    }
    return 0;
}