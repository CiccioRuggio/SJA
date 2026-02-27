#include <stdio.h>
#include <math.h>
#include <unistd.h>
#include <ctype.h>

int main(void) {

    char c;

    printf("Welcome to the character checker program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert a character to check: ");
    scanf("%c", &c);
    c = tolower((unsigned char)c);

    if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') 
    {
        printf("\nThe character '%c' is a vowel.\n", c);
    } 
    else if (c >= 'a' && c <= 'z') 
    {
        printf("\nThe character '%c' is a consonant.\n", c);
    } 
    else
    {
        printf("\nThe character '%c' is not an alphabetic character.\n", c);
    }

    return 0;
}