#include <stdio.h>
#include <math.h>
#include <stdbool.h>

void printWelcomeMessage(char programName[])
{
    printf("Welcome to the %s program!\nPress ENTER to continue...", programName);
    getchar();
}

double sum(double a, double b)
{
    return a + b;
}

double subtraction(double a, double b)
{
    return a - b;
}

double multiplication(double a, double b)
{
    return a * b;
}

double division(double a, double b)
{
    if (b != 0)
    {
        return a / b;
    }
    else
    {
        printf("Error: Division by zero is not allowed.\n");
        return 0;
    }
}

void printMenu()
{
    printf("----- MENU -----\n");
    printf("1) Sum\n");
    printf("2) Subtraction\n");
    printf("3) Multiplication\n");
    printf("4) Division\n");
    printf("0) Exit\n");
}

double *numberInsertion()
{
    double num1, num2, nums[2];
    printf("Insert the first number: ");
    scanf("%lf", &num1);
    nums[0] = num1;
    printf("Insert the second number: ");
    scanf("%lf", &num2);
    nums[1] = num2;
    return nums;
}

int readChoice()
{
    int choice = 0;
    double num1, num2;
    printf("Please select the operation (1-4): ");
    scanf("%d", &choice);
    switch (choice)
    {
    case 1:
    {
        printf("\nYou selected SUM\n");
        // double *nums = numberInsertion();
        // num1 = nums[0];
        // num2 = nums[1];
        printf("Insert the first number: ");
        scanf("%lf", &num1);
        printf("Insert the second number: ");
        scanf("%lf", &num2);
        printf("Result: %.2lf\n", sum(num1, num2));
        break;
    }
    case 2:
    {
        printf("\nYou selected SUBTRACTION\n");
        // double *nums = numberInsertion();
        // num1 = nums[0];
        // num2 = nums[1];
        printf("Insert the first number: ");
        scanf("%lf", &num1);
        printf("Insert the second number: ");
        scanf("%lf", &num2);
        printf("Result: %.2lf\n", subtraction(num1, num2));
        break;
    }

    case 3:
    {
        printf("\nYou selected MULTIPLICATION\n");
        // double *nums = numberInsertion();
        // num1 = nums[0];
        // num2 = nums[1];
        printf("Insert the first number: ");
        scanf("%lf", &num1);
        printf("Insert the second number: ");
        scanf("%lf", &num2);
        printf("Result: %.2lf\n", multiplication(num1, num2));
        break;
    }

    case 4:
    {
        printf("\nYou selected DIVISION\n");
        // double *nums = numberInsertion();
        // num1 = nums[0];
        // num2 = nums[1];
        printf("Insert the first number: ");
        scanf("%lf", &num1);
        printf("Insert the second number: ");
        scanf("%lf", &num2);
        printf("Result: %.2lf\n", division(num1, num2));
        break;
    }

    case 0:
        printf("\n-----------------");
        printf("\n|SABBINIRICA!!!!|");
        printf("\n-----------------\n");
        break;

    default:
        printf("\nInvalid choice. Please insert a number between 0 and 4:\n");
    }
    return choice;
}

bool isValidChoice(int choice)
{
    return choice >= 0 && choice <= 4;
}

void loopChoice(int choice)
{
    do
    {
        printMenu();
        choice = readChoice();
    } while (!isValidChoice(choice) && choice != 0);
}

int main()
{
    printWelcomeMessage("CALCULATOR");

    int choice = 0;
    loopChoice(choice);
}