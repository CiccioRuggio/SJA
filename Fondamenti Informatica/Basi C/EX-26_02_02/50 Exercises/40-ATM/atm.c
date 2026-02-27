// Simulare un bancomat (deposito, prelievo, saldo).
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <math.h>

int main(void)
{
    double balance = 0.0; // Initialize the account balance to zero
    int choice;
    double amount;

    printf("Welcome to the ATM simulator!\nPress ENTER to continue...");
    getchar();

    do
    {
        printf("\n======================================\n");
        printf("\nPlease choose an option from the menu:\n");
        printf("1. Deposit\n");
        printf("2. Withdraw\n");
        printf("3. Check Balance\n");
        printf("4. Exit\n\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice)
        {
        case 1: // Deposit
            printf("\nEnter the amount to deposit: ");
            scanf("%lf", &amount);
            if (amount > 0)
            {
                balance += amount; // Add the deposited amount to the balance
                printf("You have deposited $%.2lf correctly. Your new balance is $%.2lf.\n", amount, balance);
            }
            else
            {
                printf("Invalid amount. Please enter a positive number.\n");
            }
            break;
        case 2: // Withdraw
            printf("\nEnter the amount to withdraw: ");
            scanf("%lf", &amount);
            if (amount > 0 && amount <= balance)
            {
                balance -= amount; // Subtract the withdrawn amount from the balance
                printf("You have withdrawn $%.2lf correctly. Your new balance is $%.2lf.\n", amount, balance);
            }
            else if (amount > balance)
            {
                printf("Insufficient funds. Your current balance is still $%.2lf.\n", balance);
            }
            else
            {
                printf("Invalid amount. Please enter a positive number.\n");
            }
            break;
        case 3: // Check Balance
            printf("\nYour current balance is $%.2lf.\n", balance);
            break;
        case 4: // Exit
            printf("\nExiting the program. Goodbye!\n");
            break;
        default:
            printf("\nInvalid choice. Please try again.\n");
            break;
        }
    } while (choice != 4); // The loop continues until the user chooses to exit (option 4)

    return 0;
}