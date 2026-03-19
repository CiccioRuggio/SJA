#include "../../Lib/mainFunc.h"

void read_votes(int a[], int n)
{
    for (int i = 0; i < n; i++)
        a[i] = read_int_in_range("Insert a grade", 1, 30);
}

void print_array(const int a[], int n)
{
    printf("Votes: ");
    for (int i = 0; i < n; i++) {
        printf("%d", a[i]);
        if (i < n - 1)
            printf(", ");
    }
    printf("\n");
}

float average(const int a[], int n)
{
    float sum = 0;
    for (int i = 0; i < n; i++)
        sum += a[i];
    return sum / n;
}

int min_array(const int a[], int n)
{
    int min = a[0];
    for (int i = 1; i < n; i++)
        if (a[i] < min)
            min = a[i];
    return min;
}

int max_array(const int a[], int n)
{
    int max = a[0];
    for (int i = 1; i < n; i++)
        if (a[i] > max)
            max = a[i];
    return max;
}

int count_passed(const int a[], int n)
{
    int count = 0;
    for (int i = 0; i < n; i++)
        if (a[i] >= 18)
            count++;
    return count;
}

int main(void)
{
    int n;
    
    printWelcomeMessage("Grades Analyzer");
    system("clear");
    
    do {
        printf("How many votes? (1-50): ");
        scanf("%d", &n);
        if (n < 1 || n > 50)
        printf("Invalid value. Must be between 1 and 50.\n");
    } while (n < 1 || n > 50);
    
    int votes[n];
    read_votes(votes, n);
    system("clear");

    print_array(votes, n);
    printf("\n\n\n");
    printf("Average:  %.2f\n", average(votes, n));
    printf("Minimum:  %d\n", min_array(votes, n));
    printf("Maximum:  %d\n", max_array(votes, n));
    printf("Passed (grade >= 18): %d\n", count_passed(votes, n));

    return 0;
}
