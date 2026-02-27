#include <stdio.h>
#include <math.h>
#include <unistd.h>

int main(void) {

    int max = 0, n;

    printf("Welcome to the max number calculator program!\nPress ENTER to continue...");
    getchar();
    printf("Start inserting numbers to check (insert a negative number to stop): \n");
    
    scanf("%d", &n);
    max = n;
    while (n >= 0)
    {
        if (n > max)
        {
            max = n;
        }

        printf("Max so far is %d\n", max);
        printf("Please insert a positive number to check (insert a negative number to stop): \n");
        scanf("%d", &n);
    }
    
    printf("The max number is %d\n", max);
    return 0;
}