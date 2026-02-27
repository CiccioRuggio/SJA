#include <stdio.h>
#include <math.h>
#include <unistd.h>

int main(void) {

    int limit, sum = 0;
    printf("Welcome to the sum calculator program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert a number to sum from 1 to that number: ");
    scanf("%d", &limit);

    for (int n = 1; n <= limit; n++) {
        sum += n;
        printf("Interim sum at %d is %d\n", n, sum);
        sleep(1); // Pause for 1 second between sums
    }

    printf("The sum of numbers from 1 to %d is %d\n", limit, sum);
    return 0;
}