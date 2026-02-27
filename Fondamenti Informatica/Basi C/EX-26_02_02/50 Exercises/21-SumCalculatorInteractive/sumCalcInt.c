#include <stdio.h>
#include <math.h>
#include <unistd.h>

int main(void) {

    int sum = 0, n;

    printf("Welcome to the sum calculator program!\nPress ENTER to continue...");
    getchar();
    //printf("Start inserting numbers to sum (0 to stop): \n");
    
    do {
        printf("Please insert a number to sum (0 to stop): \n");
        scanf("%d", &n);
        sum += n;
        printf("Interim sum is %d\n", sum);
    } while (n != 0);
    
    printf("The sum is %d\n", sum);
    return 0;
}