// Chiedere all’utente una password finché non è corretta.
#include <stdio.h>
#include <string.h>
#include <unistd.h>

int main(void) {
    char password[20];
    const char correctPassword[] = "PasswordMoltoSicura"; // Define the correct password
    
    printf("Welcome to the password control program!\nPress ENTER to continue...");
    getchar();
    
    do {
        printf("Please enter the password: ");
        scanf("%19s", password); // Read a string with a maximum length of 19 characters
    } while (strcmp(password, correctPassword) != 0); // Compare the entered password with the correct one

    printf("\nAccess granted! Welcome!\n");
    
    return 0;
}