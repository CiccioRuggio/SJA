#include <stdio.h>

void printWelcomeMessage(char programName[])
{
    printf("Welcome to the %s program!\nPress ENTER to continue...", programName);
    getchar();
}

int main()
{
    system("clear");
    printWelcomeMessage("NUMBERS");

    int count = 0, counterNum = 0, max = 0, min = 0, num, count18 = 0;
    float sum = 0.0;
    int validInput = 0;

    // --- Validate the count input ---
    do
    {
        printf("Please insert a number, that's how many numbers you will be asked to insert (1-50): ");
        if (scanf("%d", &count) == 1 && count > 0 && count <= 50)
        {
            validInput = 1;
        }
        else
        {
            printf("Invalid input. Please insert a number between 1 and 50.\n");
            while (getchar() != '\n' && getchar() != EOF); // flush leftover junk from buffer
            validInput = 0;
        }
    } while (!validInput);

    // --- Collect 'count' numbers ---
    while (counterNum < count)
    {
        printf("Insert a number: ");
        if (scanf("%d", &num) == 1 && num >= 0 && num <= 30)
        {
            counterNum++;
            if (counterNum == 1)
            {
                max = num;
                min = num;
            }
            if (num > max)
            {
                max = num;
            };
            if (num < min)
            {
                min = num;
            };
            if (num >= 18)
            {
                count18++;
            };
            sum += num;
        }
        else
        {
            printf("Invalid input. Please insert a positive integer (0-30).\n");
            while (getchar() != '\n' && getchar() != EOF); // flush junk and retry
        }
    }

    // --- Print results ---
    if (count > 0)
    {
        printf("You inserted %d numbers, max: %d, min: %d, average: %.2f, numbers >= 18: %d\n", counterNum, max, min, sum / counterNum, count18);
    }
    else
    {
        printf("You inserted %d numbers.\n", counterNum);
    }

    return 0;
}