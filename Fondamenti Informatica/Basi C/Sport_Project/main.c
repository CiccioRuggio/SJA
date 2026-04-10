#include "../Lib/mainFunc.h"
#include <string.h>
#include <stdbool.h>

#define MAX_SUBSCRIBER 100

struct Subscription
{
    int typeSub;
    int subPeriod;
    float monthsPrice;
    float totSub;
    bool subState;
};

struct User
{
    int id;
    char name[30];
    char surname[30];
    int age;
    struct Subscription sub;
};

int find_index(int codes[], int count, int code)
{
    int i;
    for (i = 0; i < count; i++)
    {
        if (codes[i] == code)
            return i;
    }
    return -1;
}

void insertUser(struct User users[], int count, int codes[])
{
    struct User user;
    // ID is generated from the system, user will not put it by himself
    codes[count] = count + 1;
    user.id = count + 1;
    printf("Name: ");
    scanf("%s", user.name);
    printf("Surname: ");
    scanf("%s", user.surname);
    printf("Age: ");
    scanf("%d", &user.age);

    // Subscription is not created anew from the user at this point, it will be corrected by user in another func
    user.sub.subState = false;
    user.sub.monthsPrice = 0.0;
    user.sub.subPeriod = 0;
    user.sub.totSub = 0.0;
    user.sub.typeSub = 2;

    users[count] = user;
}

int searchById(struct User users[], int count, int codes[])
{
    int searchedCode;
    int index;
    printf("Enter code: ");
    scanf("%d", &searchedCode);
    index = find_index(codes, count, searchedCode);
    if (index != -1)
    {
        printf("Id: %d\n", users[index].id);
        printf("Name: %s\n", users[index].name);
        printf("Surname: %s\n", users[index].surname);
        printf("Age: %d\n", users[index].age);
    }
    else
    {
        printf("User not found.\n");
    }

    return index;
}

void printAllUsers(struct User users[], int count)
{
    if (count == 0)
    {
        printf("Users not found!");
    }
    else
    {
        for (int i = 0; i < count; i++)
        {
            printf("Id: %d\n", users[i].id);
            printf("Name: %s\n", users[i].name);
            printf("Surname: %s\n", users[i].surname);
            printf("Age: %d\n", users[i].age);
            printf("Monthly Price: %f\n", users[i].sub.monthsPrice);
        }
    }
}

void setPrice(struct User users[], int count)
{
    float newPrice;
    int typeSub;
    printf("Insert new price: ");
    scanf("%f", &newPrice);
    typeSub = read_int_in_range("Insert subscription type: ", 1, 3);

    if (newPrice > 0)
    {
        switch (typeSub)
        {
        case 1:
            for (int i = 0; i < count; i++)
            {
                if (users[i].sub.typeSub == typeSub)
                {
                    users[i].sub.monthsPrice = newPrice;
                    printf("Price updated.\n");
                }
            }
            printf("End of cycle");
            break;
        case 2:
            for (int i = 0; i < count; i++)
            {
                if (users[i].sub.typeSub == typeSub)
                {
                    users[i].sub.monthsPrice = newPrice;
                    printf("Price updated.\n");
                }
            }
            printf("End of cycle");
            break;
        case 3:
            for (int i = 0; i < count; i++)
            {
                if (users[i].sub.typeSub == typeSub)
                {
                    users[i].sub.monthsPrice = newPrice;
                    printf("Price updated.\n");
                }
            }
            printf("End of cycle");
            break;
        default:
            printf("Invalid typeSub");
        }
    }
    else
    {
        printf("You inserted %f, not good my bro", newPrice);
    }
}

int printMenu()
{
    int choice;
    printf("\n--- SPORTS CENTER MANAGEMENT ---\n");
    printf("1. Insert new subscriber\n");   // FATTO
    printf("2. Show all subscribers\n");    // FATTO
    printf("3. Search subscriber by ID\n"); // FATTO
    printf("4. Update subscription status\n");
    printf("5. Update purchased months\n");
    printf("6. Update monthly cost\n"); // FATTO
    printf("7. Calculate total cost of one subscription\n"); // FATTO
    printf("8. Calculate total theoretical revenue\n");
    printf("9. Count active subscribers\n");
    printf("10. Count subscribers by subscription type\n");
    printf("11. Find subscriber with highest total cost\n");
    printf("12. Calculate average age\n");
    // users[]
    // codes[]
    // totAge += user.age;
    // counter++
    // ageAvg = totAge / counter;
    printf("0. Exit\n");

    choice = read_int_in_range("Choice: ", 0, 12);
    return choice;
}

int main()
{
    int codes[MAX_SUBSCRIBER];
    struct User users[MAX_SUBSCRIBER];
    int count = 0;
    int choice;

    do
    {
        choice = printMenu();
        switch (choice)
        {
        case 1:
            insertUser(users, count, codes);
            count++;
            break;
        case 2:
            printAllUsers(users, count);
            break;
        case 3:
        {
            int idx = searchById(users, count, codes);
            break;
        }
        case 4:

            break;
        case 5:

            break;
        case 6:
        {
            setPrice(users, count);
            break;
        }
        case 7:
            break;
        case 8:

            break;
        case 9:

            break;
        case 10:

            break;
        case 11:

            break;
        case 12:

            break;
        case 0:
            printf("Bye bye!");
            break;
        default:

            break;
        }
    } while (choice != 0);

    return 0;
}