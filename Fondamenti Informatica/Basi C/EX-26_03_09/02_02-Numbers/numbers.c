#include <math.h>
#include <stdbool.h>
#include "../../Lib/mainFunc.h"

int main()
{
    system("clear");
    printWelcomeMessage("NUMBERS");

    int count = 0, counterNum = 0, max, min, num, count18 = 0;
    float sum = 0.0;

    printf("Please insert a number, that's how many numbers you will be asked to insert (0-30): ");
    scanf("%d", &count);
    do
    {
        if (count >= 0 && count <= 30 && getchar() == '\n')
        {
            while (counterNum <= count && count != 0)
            {
                printf("Insert a number: ");
                scanf("%d", &num);

                if (getchar() != '\n' || num < 0)
                {
                    printf("Invalid input. Please insert a positive integer.\n\n\nSONO A RIGA 32!!!!\n");
                    continue;
                }
                else if (num >= 0)
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
                    }
                    if (num < min)
                    {
                        min = num;
                    }
                    if (num >= 18)
                    {
                        count18++;
                    }

                    sum += num;
                    break;
                }
            }
        }

        else
        {
            printf("Invalid input. Please insert a positive integer.\n\n\n---------SONO A RIGA 64!!!\n");
            continue;
        }

    } while (true);

    printf("You inserted %d numbers, the maximum is %d, the minimum is %d, the average is %.2f and %d of the numbers you inserted are greater than or equal to 18.\n", counterNum, max, min, sum / counterNum, count18);
}