#include <stdio.h>
#include <math.h>
#include <unistd.h>

int main(void) {

    int n;
    
    printf("Welcome to the TABELLINE calculator program!\nPress ENTER to continue...");
    getchar();
    printf("Please insert a number to calculate its multiplication table: ");
    scanf("%d", &n);
    for (int i = 1; i <= 10; i++) {
        printf("%d x %d = %d\n", n, i, n*i);
        sleep(1); // Pause for 1 second between lines
    }
    return 0;
}