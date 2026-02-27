#include <stdio.h>
#include <math.h>

double sum(double a, double b) {
    return a + b;
}

double avg(double a, double b) {
    return sum(a, b) / 2.0;
}

void printWelcomeMessage(char *programName) {
    printf("Welcome to the %s program!\nPress ENTER to continue...", programName);
    getchar();
}

int main(void) {

    int num1, num2;
    
    printWelcomeMessage("sum calculator");
    printf("Please insert the first integer: ");
    scanf("%d", &num1);
    printf("Please insert the second integer: ");
    scanf("%d", &num2);

    // int result = sum(num1, num2);
    printf("\nThe sum of %d and %d is: %.2f\n", num1, num2, sum(num1, num2));
    printf("\nThe average of %d and %d is: %.2f\n", num1, num2, avg(num1, num2));

    return 0;
}