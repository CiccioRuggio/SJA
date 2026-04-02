#include "../Lib/mainFunc.h"
#include <string.h>
#include <stdbool.h>

#define MAX_SUBSCRIBER 100

struct Subscription {
    int typeSub;
    int subPeriod;
    float monthsPrice;
    float totSub;
    bool subState;
};

struct User {
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

void setPrice(struct User *user, float newPrice)
{
    user->sub.monthsPrice = newPrice;
    printf("Price updated.\n");
}

void insertUser(struct User users[], int position)
{
    struct User user;
    //ID is generated from the system, user will not put it by himself
    user.id = position + 1;
    printf("Name: ");
    scanf("%s", user.name);
    printf("Surname: ");
    scanf("%s", user.surname);
    printf("Age: ");
    scanf("%d", &user.age);

    //Subscription is not created anew from the user at this point, it will be corrected by user in another func
    user.sub.subState = false;
    user.sub.monthsPrice = 0.0;
    user.sub.subPeriod = 0;
    user.sub.totSub = 0.0;
    user.sub.typeSub = 0;

    // TODO: subscription, id
    users[position] = user;
}

int printMenu()
{
    int choice;
    printf("\n--- SPORTS CENTER MANAGEMENT ---\n");
    printf("1. Insert new subscriber\n");
    printf("2. Show all subscribers\n");
    printf("3. Search subscriber by ID\n");
    printf("4. Update subscription status\n");
    printf("5. Update purchased months\n");
    printf("6. Update monthly cost\n");
    printf("7. Calculate total cost of one subscription\n");
    printf("8. Calculate total theoretical revenue\n");
    printf("9. Count active subscribers\n");
    printf("10. Count subscribers by subscription type\n");
    printf("11. Find subscriber with highest total cost\n");
    printf("12. Calculate average age\n");
    printf("13. Exit\n");

    choice = read_int_in_range("Choice: ", 1, 13);
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
            insertUser(users, count);
            count++;
            break;
        case 2:

            break;
        case 3:
        {
            int searchedCode;
            int index;
            printf("Enter code: ");
            scanf("%d", &searchedCode);
            index = find_index(codes, count, searchedCode);
            if (index != -1) {
                printf("Id: %d\n", users[index].id);
                printf("Name: %s\n", users[index].name);
                printf("Surname: %s\n", users[index].surname);
                printf("Age: %d\n", users[index].age);
            } else {
                printf("User not found.\n");
            }
            break;
        }
        case 4:

            break;
        case 5:

            break;
        case 6:
        {
            float newPrice;
            int id;
            int idx;
            printf("Insert the id of the user: ");
            scanf("%d", &id);
            idx = find_index(codes, count, id);
            if (idx != -1) {
                printf("Insert the new price: ");
                scanf("%f", &newPrice);
                setPrice(&users[idx], newPrice);
            } else {
                printf("User not found.\n");
            }
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
        case 13:

            break;
        default:

            break;
        }
    } while (choice != 13);

    return 0;
}
