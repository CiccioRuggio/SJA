#include <stdio.h>
#include <math.h>

int main (void) {
    int grade;
    char gradeLetter[] = "Your grade is: ";

    printf("Welcome to the grade checker program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert your grade (0-30): ");
    scanf("%d", &grade);

    if (grade >= 26 && grade <= 30) {
        printf("%sOttimo!\n", gradeLetter);
    } else if (grade >= 22 && grade < 26) {
        printf("%sBuono!\n", gradeLetter);
    } else if (grade >= 18 && grade < 22) {
        printf("%sSufficiente!\n", gradeLetter);
    } else if (grade >= 0 && grade < 18) {
        printf("%sInsufficiente!\n", gradeLetter);
    } else {
        printf("Invalid grade entered. Please enter a grade between 0 and 30.\n");
    }

    return 0;
}