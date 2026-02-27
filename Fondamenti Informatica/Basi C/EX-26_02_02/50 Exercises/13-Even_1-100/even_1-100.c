#include <stdio.h>
#include <math.h>
#include <unistd.h>

int main(void) {

    printf("Welcome to the number printer program!\nPress ENTER to continue...");
    getchar();
    printf("The program will now start printing even numbers from 1 to 100:\nPress ENTER to begin...");
    getchar();
    // for (int n = 2; n <= 100; n += 2) {
    //     printf("%d\n", n);
    //     sleep(1); // Pause for 1 second between numbers
    // }

    for (int n = 1; n <= 100; n++) {
        if (n % 2 == 0) {
            printf("%d\n", n);
            sleep(1); // Pause for 1 second between numbers
        }
    }
    
    return 0;
}