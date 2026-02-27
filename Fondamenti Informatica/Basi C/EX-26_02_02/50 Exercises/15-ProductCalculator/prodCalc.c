#include <stdio.h>
#include <math.h>
#include <unistd.h>

int main(void) {

    int limit, product = 1;

    printf("Welcome to the product calculator program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert a number to calculate the product from 1 to that number: ");
    scanf("%d", &limit);

    for (int n = 1; n <= limit; n++) {
        product *= n;
        printf("Interim product at %d is %d\n", n, product);
        sleep(1); // Pause for 1 second between products
    }

    printf("The factorial of %d is %d\n", limit, product);
    return 0;
}