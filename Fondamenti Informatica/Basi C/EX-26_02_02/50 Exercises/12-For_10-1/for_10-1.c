#include <stdio.h>
#include <math.h>
#include <unistd.h>

int main(void) {

    printf("Welcome to the number printer program!\nPress ENTER to continue...");
    getchar();
    printf("The program will now start printing numbers from 10 to 1:\nPress ENTER to begin...");
    getchar();
    for (int n = 10; n >= 1; n--) {
        printf("%d\n", n);
        sleep(1); // Pause for 1 second between numbers
    }
    return 0;
}