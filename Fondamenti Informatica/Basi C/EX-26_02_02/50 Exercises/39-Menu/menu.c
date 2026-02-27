// Simulare un menu con più opzioni usando un ciclo.
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <math.h>

int main(void) {
    int choice;
    printf("Welcome to the menu program!\nPress ENTER to continue...");
    getchar();

    do {
        printf("\nPlease choose an option from the menu:\n"); // Fictitious menu options for demonstration purposes. The loop will just continue to display the menu and prompt the user for a choice until they select the option to exit (option 4).
        printf("1. Option 1\n");
        printf("2. Option 2\n");
        printf("3. Option 3\n");
        printf("4. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                printf("\nYou chose Option 1!\n");
                break;
            case 2:
                printf("\nYou chose Option 2!\n");
                break;
            case 3:
                printf("\nYou chose Option 3!\n");
                break;
            case 4:
                printf("\nExiting the program. Goodbye!\n");
                break;
            default:
                printf("\nInvalid choice. Please try again.\n");
                break;
        }
    } while (choice != 4); // The loop continues until the user chooses to exit (option 4)

    return 0;
}