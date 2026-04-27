#include "../Lib/mainFunc.h"
#include <string.h>
#include <stdbool.h>

#define MAX_SUBSCRIBER 100
#define POOL_TYPE "Pool"
#define GYM_TYPE "Gym"
#define FULL_TYPE "Full"
#define POOL_PRICE 5.00
#define GYM_PRICE 10.00
#define FULL_PRICE 12.00
float updatablePool = POOL_PRICE;
float updatableGym = GYM_PRICE;
float updatableFull = FULL_PRICE;

typedef struct 
{
    int typeSub;
    int subPeriod;
    float monthsPrice;
    float totSub;
    bool subState;
}Subscription;

typedef struct
{
    int id;
    char name[30];
    char surname[30];
    int age;
    Subscription sub;
}User;

// Here we create some hard-coded users to have something to see and work on at the start of the program
User user1 = {
    .id = 1,
    .name = "Lorenzo",
    .surname = "Alfè",
    .age = 19,
    .sub = {
        .typeSub = 1,
        .subPeriod = 4,
        .monthsPrice = POOL_PRICE,
        .totSub = 4 * POOL_PRICE,
        .subState = true}};

User user2 = {
    .id = 2,
    .name = "Francesco",
    .surname = "Ruggeri",
    .age = 27,
    .sub = {
        .typeSub = 2,
        .subPeriod = 13,
        .monthsPrice = GYM_PRICE,
        .totSub = 13 * GYM_PRICE,
        .subState = true}};

User user3 = {
    .id = 3,
    .name = "Gianmarco",
    .surname = "Cagliari",
    .age = 26,
    .sub = {
        .typeSub = 3,
        .subPeriod = 24,
        .monthsPrice = FULL_PRICE,
        .totSub = 24 * FULL_PRICE,
        .subState = true}};

User user4 = {
    .id = 4,
    .name = "Stefano",
    .surname = "Fortezza",
    .age = 27,
    .sub = {
        .typeSub = 3,
        .subPeriod = 3,
        .monthsPrice = FULL_PRICE,
        .totSub = 3 * FULL_PRICE,
        .subState = true}};

User user5 = {
    .id = 5,
    .name = "Andrea",
    .surname = "Calanna",
    .age = 25,
    .sub = {
        .typeSub = 1,
        .subPeriod = 16,
        .monthsPrice = POOL_PRICE,
        .totSub = 16 * POOL_PRICE,
        .subState = true}};

User user6 = {
    .id = 6,
    .name = "Fabio",
    .surname = "Lanzafame",
    .age = 35,
    .sub = {
        .typeSub = 0,
        .subPeriod = 0,
        .monthsPrice = 0.0,
        .totSub = 0.0,
        .subState = false}};

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

void printTypeSub(int type)
{
    switch (type)
    {
    case 1:
        printf("Subscription type: Pool\n");
        break;
    case 2:
        printf("Subscription type: Gym\n");
        break;
    case 3:
        printf("Subscription type: Full\n");
        break;
    case 0:
        printf("This user has not currently subscribed to any service\n");
        break;
    default:
        printf("INVALID USER TYPE\n");
        break;
    }
}

void printSingleUser(User user)
{
    printf("Id: %d\n", user.id);
    printf("Name: %s\n", user.name);
    printf("Surname: %s\n", user.surname);
    printf("Age: %d\n", user.age);
    printTypeSub(user.sub.typeSub);
    printf("Current Status: %s (%d months remaining)\n",
           user.sub.subState ? "ACTIVE" : "INACTIVE",
           user.sub.subPeriod);
}

void insertUser(User users[], int count, int codes[]) // FUNCTION 1
{
    User user;
    // ID is generated from the system, user will not put it by himself
    codes[count] = count + 1;
    user.id = count + 1;
    printf("\n--- Insert new Subscriber ---\n");
    printf("Name: ");
    scanf("%s", user.name);
    printf("Surname: ");
    scanf("%s", user.surname);
    printf("Age: ");
    scanf("%d", &user.age);

    int subPeriodIpt = 0;
    int typeSubIpt = read_int_in_range("Insert the type of subscription you want to purchase\n1) Pool\n2) Gym\n3) Full\n0) INACTIVE\n", 0, 3);
    switch (typeSubIpt)
    {
    case 1:
        subPeriodIpt = read_int_in_range("Insert the number of months you want to purchase", 1, 12);
        if (subPeriodIpt > 0 && subPeriodIpt <= 12)
        {
            user.sub.subState = true;
            user.sub.subPeriod = subPeriodIpt;
            user.sub.monthsPrice = updatablePool;
            user.sub.totSub = (user.sub.monthsPrice * user.sub.subPeriod);
            user.sub.typeSub = typeSubIpt;
            printf("User %s %s inserted CORRECTLY", user.name, user.surname);
        }
        break;
    case 2:
        subPeriodIpt = read_int_in_range("Insert the number of months you want to purchase", 1, 12);
        if (subPeriodIpt > 0 && subPeriodIpt <= 12)
        {
            user.sub.subState = true;
            user.sub.subPeriod = subPeriodIpt;
            user.sub.monthsPrice = updatableGym;
            user.sub.totSub = (user.sub.monthsPrice * user.sub.subPeriod);
            user.sub.typeSub = typeSubIpt;
            printf("User %s %s inserted CORRECTLY", user.name, user.surname);
        }
        break;
    case 3:
        subPeriodIpt = read_int_in_range("Insert the number of months you want to purchase", 1, 12);
        if (subPeriodIpt > 0 && subPeriodIpt <= 12)
        {
            user.sub.subState = true;
            user.sub.subPeriod = subPeriodIpt;
            user.sub.monthsPrice = updatableFull;
            user.sub.totSub = (user.sub.monthsPrice * user.sub.subPeriod);
            user.sub.typeSub = typeSubIpt;
            printf("User %s %s inserted CORRECTLY", user.name, user.surname);
        }
        break;
    case 0:
        user.sub.subState = false;
        user.sub.subPeriod = 0;
        user.sub.monthsPrice = 0.0;
        user.sub.totSub = 0.0;
        user.sub.typeSub = 0;
        printf("User %s %s was inserted as INACTIVE", user.name, user.surname);
        break;
    default:
        printf("User NOT inserted");
        break;
    }

    users[count] = user;
}

void printAllUsers(User users[], int count) // FUNCTION 2
{
    printf("\n--- Show all Subscribers in database ---\n");
    if (count == 0)
    {
        printf("\nUsers not found!");
    }
    else
    {
        printf("\n================ START OF USERS PRINTS ================\n\n");
        for (int i = 0; i < count; i++)
        {
            printf("--------------------------------\n");
            printSingleUser(users[i]);
        }
        printf("--------------------------------\n");
    }
}

int searchById(User users[], int count, int codes[]) // FUNCTION 3
{
    int searchedCode;
    int index;
    printf("\n--- Researching a Subscriber ---\n");
    printf("Enter code ID: ");
    scanf("%d", &searchedCode);
    index = find_index(codes, count, searchedCode);
    if (index != -1)
    {
        printf("--------------------------------\n");
        printSingleUser(users[index]);
        printf("--------------------------------\n");
    }
    else
    {
        printf("User not found.\n");
    }

    return index;
}

void updateSubStatus(User users[], int codes[], int count) // FUNCTION 4
{
    int searchedCode, index, choice;
    printf("--- Update Subscription Status ---\n");
    printf("Enter User ID: ");
    scanf("%d", &searchedCode);

    index = find_index(codes, count, searchedCode);

    if (index != -1)
    {
        printf("User: %s %s\n", users[index].name, users[index].surname);
        printf("Current Status: %s (%d months remaining)\n",
               users[index].sub.subState ? "ACTIVE" : "INACTIVE",
               users[index].sub.subPeriod);

        printf("\nSelect action:\n");
        printf("1. Activate (Sets to 1 month if inactive)\n");
        printf("0. Deactivate subscription\n");
        printf("Choice: ");
        scanf("%d", &choice);

        if (choice == 1)
        {
            users[index].sub.subState = true;
            if (users[index].sub.subPeriod == 0)
            {
                users[index].sub.subPeriod = 1;
            }

            users[index].sub.totSub = users[index].sub.monthsPrice * (float)users[index].sub.subPeriod;
            printf("Subscription ACTIVATED.\n");
        }
        else if (choice == 0)
        {
            users[index].sub.subState = false;
            users[index].sub.subPeriod = 0;
            users[index].sub.totSub = 0.0;
            users[index].sub.monthsPrice = 0.0;
            users[index].sub.typeSub = 0;
            printf("Subscription DEACTIVATED and months cleared.\n");
        }
        else
        {
            printf("Invalid choice.\n");
        }
    }
    else
    {
        printf("Error ID not found.\n");
    }
}

void updatePurchasedMonths(User users[], int codes[], int count) // FUNCTION 5
{
    int searchedCode, index, newMonths;

    printf("--- Purchase/Renew Months ---\n");
    printf("Enter ID: ");
    scanf("%d", &searchedCode);

    index = find_index(codes, count, searchedCode);

    if (index != -1)
    {
        newMonths = read_int_in_range("Enter the number of months to purchase", 1, 12);

        switch (users[index].sub.typeSub)
        {
            case 1:
                users[index].sub.monthsPrice = updatablePool;
                users[index].sub.subPeriod += newMonths;
                users[index].sub.totSub = users[index].sub.monthsPrice * users[index].sub.subPeriod;
                users[index].sub.subState = true;
                printf("User: %s %s\n", users[index].name, users[index].surname);
                printf("Purchase successful!\n");
                printf("Status: Active | New Total: %.2f\n", users[index].sub.totSub);
                break;
            case 2:
                users[index].sub.monthsPrice = updatableGym;
                users[index].sub.subPeriod += newMonths;
                users[index].sub.totSub = users[index].sub.monthsPrice * users[index].sub.subPeriod;
                users[index].sub.subState = true;
                printf("User: %s %s\n", users[index].name, users[index].surname);
                printf("Purchase successful!\n");
                printf("Status: Active | New Total: %.2f\n", users[index].sub.totSub);
                break;
            case 3:
                users[index].sub.monthsPrice = updatableFull;
                users[index].sub.subPeriod += newMonths;
                users[index].sub.totSub = users[index].sub.monthsPrice * users[index].sub.subPeriod;
                users[index].sub.subState = true;
                printf("User: %s %s\n", users[index].name, users[index].surname);
                printf("Purchase successful!\n");
                printf("Status: Active | New Total: %.2f\n", users[index].sub.totSub);
                break;
            case 0:
                users[index].sub.monthsPrice = 0.0;
                users[index].sub.subPeriod = 0;
                users[index].sub.totSub = 0.0;
                users[index].sub.subState = false;
                printf("User: %s %s\n", users[index].name, users[index].surname);
                printf("Purchase failed!\n");
                printf("Status: Inactive | Total: %.2f\n", users[index].sub.totSub);
            default:
                printf("Invalid typeSub");
        }
    }
    else
    {
        printf("Error! User not found.\n");
    }
}

void setPrice(User users[], int count) // FUNCTION 6
{
    float newPrice;
    int typeSub;
    printf("\n--- Update monthly price ---\n");
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
                    updatablePool = newPrice;
                    users[i].sub.monthsPrice = newPrice;
                    users[i].sub.totSub = newPrice * users[i].sub.subPeriod;
                    printf("Price updated for Subscriber with ID: %d.\n", users[i].id);
                }
            }
            printf("End of cycle");
            break;
        case 2:
            for (int i = 0; i < count; i++)
            {
                if (users[i].sub.typeSub == typeSub)
                {
                    updatableGym = newPrice;
                    users[i].sub.monthsPrice = newPrice;
                    users[i].sub.totSub = newPrice * users[i].sub.subPeriod;
                    printf("Price updated for Subscriber with ID: %d.\n", users[i].id);
                }
            }
            printf("End of cycle");
            break;
        case 3:
            for (int i = 0; i < count; i++)
            {
                if (users[i].sub.typeSub == typeSub)
                {
                    updatableFull = newPrice;
                    users[i].sub.monthsPrice = newPrice;
                    users[i].sub.totSub = newPrice * users[i].sub.subPeriod;
                    printf("Price updated for Subscriber with ID: %d.\n", users[i].id);
                }
            }
            printf("End of cycle");
            break;
        case 0:
            for (int i = 0; i < count; i++)
            {
                if (users[i].sub.typeSub == typeSub)
                {
                    users[i].sub.monthsPrice = 0.0;
                    users[i].sub.subPeriod = 0;
                    users[i].sub.totSub = 0.0;
                    printf("Price updated for Subscriber with ID: %d.\n", users[i].id);
                }
            }
        default:
            printf("Invalid typeSub");
        }
    }
    else
    {
        printf("You inserted %f, not good my bro", newPrice);
    }
}

void showTotSub(User users[], int count, int codes[]) // FUNCTION 7
{
    int index = searchById(users, count, codes);
    if (index != -1)
    {
        printf("Total subscription cost: %.2f\n", users[index].sub.totSub);
    }
}

float calculateTotRevenue(User users[], int count) // FUNCTION 8
{
    printf("\n--- Calculate the total revenue of the System ---\n");
    float totRevenue = 0.0;
    for (int i = 0; i < count; i++)
    {
        totRevenue += users[i].sub.totSub;
    }
    return totRevenue;
}

int countActive(User users[], int count) // FUNCTION 9
{
    printf("\n--- Count of ACTIVE Subscribers ---\n");
    int countActive = 0;
    for (int i = 0; i < count; i++)
    {
        if (users[i].sub.subState)
        {
            countActive++;
        }
    }
    return countActive;
}

void countActivePerType(User users[], int count) // FUNCTION 10
{
    printf("\n--- Count of ACTIVE Subscribers per type of subscription ---\n");
    int countPool = 0, countGym = 0, countFull = 0;
    for (int i = 0; i < count; i++)
    {
        switch (users[i].sub.typeSub)
        {
        case 1:
            countPool++;
            break;
        case 2:
            countGym++;
            break;
        case 3:
            countFull++;
            break;
        }
    }
    printf("Total of pool subs: %d\n", countPool);
    printf("Total of gym subs: %d\n", countGym);
    printf("Total of full subs: %d\n", countFull);
}

void printPaperone(User users[], int count) // FUNCTION 11
{
    printf("\n--- Researching the Subscriber with the highest subscription cost ---\n");
    float max = 0.0;
    int index = 0;
    for (int i = 0; i < count; i++)
    {
        if (users[i].sub.subState)
        {
            if (users[i].sub.totSub > max)
            {
                max = users[i].sub.totSub;
                index = i;
            }
        }
    }

    printf("\nThe user with the highest subscription cost is:");
    printf("\nID: %d", users[index].id);
    printf("\nName: %s", users[index].name);
    printf("\nSurname: %s\n", users[index].surname);
    printTypeSub(users[index].sub.typeSub);
    printf("\nTotal months payed: %d", users[index].sub.subPeriod);
    printf("\nTotal payed: %.2f", users[index].sub.totSub);
}

float calculateAverageAge(User users[], int count) // FUNCTION 12
{
    printf("\n--- Calculate avarage age of Subscribers in database ---\n");
    float sum = 0.0;

    for (int i = 0; i < count; i++)
    {
        sum += users[i].age;
    }

    return sum / count;
}

int printMenu()
{
    int choice;
    printf("\n--- SPORTS CENTER MANAGEMENT ---\n");
    printf("1. Insert new subscriber\n");                       // FATTO
    printf("2. Show all subscribers\n");                        // FATTO
    printf("3. Search subscriber by ID\n");                     // FATTO
    printf("4. Update subscription status\n");                  // FATTO
    printf("5. Update purchased months\n");                     // FATTO
    printf("6. Update monthly cost\n");                         // FATTO
    printf("7. Calculate total cost of one subscription\n");    // FATTO
    printf("8. Calculate total theoretical revenue\n");         // FATTO
    printf("9. Count active subscribers\n");                    // FATTO
    printf("10. Count subscribers by subscription type\n");     // FATTO
    printf("11. Find subscriber with highest total cost\n");    // FATTO
    printf("12. Calculate average age\n");                      // FATTO
    printf("0. Exit\n");

    choice = read_int_in_range("Choice: ", 0, 12);
    return choice;
}

void runMain()
{
    int codes[MAX_SUBSCRIBER];
    User users[MAX_SUBSCRIBER];
    int choice;

    users[0] = user1;
    codes[0] = users[0].id;
    users[1] = user2;
    codes[1] = users[1].id;
    users[2] = user3;
    codes[2] = users[2].id;
    users[3] = user4;
    codes[3] = users[3].id;
    users[4] = user5;
    codes[4] = users[4].id;
    users[5] = user6;
    codes[5] = users[5].id;
    int count = 6;

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
            searchById(users, count, codes);
            break;
        case 4:
            updateSubStatus(users, codes, count);
            break;
        case 5:
            updatePurchasedMonths(users, codes, count);
            break;
        case 6:
            setPrice(users, count);
            break;
        case 7:
            showTotSub(users, count, codes);
            break;
        case 8:
            printf("The total theoretical revenue is: %.2f", calculateTotRevenue(users, count));
            break;
        case 9:
            printf("The number of active users at the moment is: %d", countActive(users, count));
            break;
        case 10:
            countActivePerType(users, count);
            break;
        case 11:
            printPaperone(users, count);
            break;
        case 12:
            printf("The average age is: %.2f", calculateAverageAge(users, count));
            break;
        case 0:
            printf("Bye bye!");
            break;
        default:
            printf("INVALID INPUT, TRY AGAIN!\n");
            break;
        }
    } while (choice != 0);
}

int main()
{
    runMain();
    return 0;
}