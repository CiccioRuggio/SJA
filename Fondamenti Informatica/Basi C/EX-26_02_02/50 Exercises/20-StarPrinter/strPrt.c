#include <stdio.h>
#include <math.h>
#include <unistd.h>
#include <stdlib.h>

int main(void) {
    system("clear");

    int n;
    printf("Welcome to the star printer program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert a positive integer: ");
    scanf("%d", &n);

    for (int i = 1; i <= n; i++) 
    {
        printf("* ");    
    }
    printf("\nHere are %d stars!\n", n);
    return 0;
}