#include <stdio.h>
#include <math.h>
#include <unistd.h>

int main(void) {

    int n1,n2;
    char op =' ';

    printf("Welcome to the calculator program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert the first number to calculate: ");
    scanf("%d", &n1);
    printf("\nPlease insert the operator (+, -, *, /): ");
    scanf(" %c", &op);
    printf("\nPlease insert the second number to calculate: ");
    scanf("%d", &n2);

    switch(op) {
        case '+':
            printf("\nThe result is: %d\n", n1+n2);
            break;
        case '-':
            printf("\nThe result is: %d\n", n1-n2);
            break;
        case '*':
            printf("\nThe result is: %d\n", n1*n2);
            break;
        case '/':
            if(n2 != 0) {
                printf("\nThe result is: %f\n", (float)n1/n2);
            } else {
                printf("\nError: Division by zero is not allowed.\n");
            }
            break;
        default:
            printf("\nError: Invalid operator.\n");
    }

    return 0;
}