#include <stdio.h>
#include <math.h>
#include <unistd.h>

int main(void) {

    int n;
    printf("Welcome to the digit counter program!\nPress ENTER to continue...");
    getchar();
    printf("Insert a positive integer number: ");
    scanf("%d", &n);
    int count = 0;
    while (n > 0) {
        n /= 10;
        count++;
    }
    printf("The number of digits is: %d\n", count);
    return 0;
}