#include <stdio.h>
#include <string.h>

#define MAX_PRODUCTS 100
#define NAME_LENGTH 30

struct Product
{
    int code;
    char name[NAME_LENGTH];
    float price;
    int quantity;
};

int showMenu()
{
    int choice;
    printf("\n--- PRODUCT MANAGEMENT ---\n");
    printf("1. Insert product\n");
    printf("2. Show all products\n");
    printf("3. Search product by code\n");
    printf("4. Update price\n");
    printf("5. Update quantity\n");
    printf("6. Sell product\n");
    printf("7. Add stock\n");
    printf("8. Calculate warehouse value\n");
    printf("9. Exit\n");
    printf("Choice: ");
    scanf("%d", &choice);
    return choice;
}

void insertProduct(struct Product warehouse[], int position)
{
    printf("Code: ");
    scanf("%d", &warehouse[position].code);

    printf("Name: ");
    scanf("%s", warehouse[position].name);

    printf("Price: ");
    scanf("%f", &warehouse[position].price);

    printf("Quantity: ");
    scanf("%d", &warehouse[position].quantity);
}

void showProducts(struct Product warehouse[], int count)
{
    int i;

    if (count == 0)
    {
        printf("No products available.\n");
    }
    else
    {
        printf("\n%-10s %-20s %-10s %-10s\n", "CODE", "NAME", "PRICE", "QUANTITY");
        for (i = 0; i < count; i++)
        {
            printf("%-10d %-20s %-10.2f %-10d\n",
                   warehouse[i].code,
                   warehouse[i].name,
                   warehouse[i].price,
                   warehouse[i].quantity);
        }
    }
}

int findProductIndex(struct Product warehouse[], int count, int searchedCode)
{
    int i;
    for (i = 0; i < count; i++)
    {
        if (warehouse[i].code == searchedCode)
        {
            return i;
        }
    }
    return -1;
}

int main()
{
    struct Product warehouse[MAX_PRODUCTS];
    int count = 0;
    int choice;

    do
    {
        choice = showMenu();

        if (choice == 1)
        {
            if (count < MAX_PRODUCTS)
            {
                insertProduct(warehouse, count);
                count++;
            }
            else
            {
                printf("Archive full.\n");
            }
        }
        else if (choice == 2)
        {
            showProducts(warehouse, count);
        }
        else if (choice == 3)
        {
            int searchedCode;
            int index;

            printf("Enter code: ");
            scanf("%d", &searchedCode);

            index = findProductIndex(warehouse, count, searchedCode);

            if (index != -1)
            {
                printf("Code: %d\n", warehouse[index].code);
                printf("Name: %s\n", warehouse[index].name);
                printf("Price: %.2f\n", warehouse[index].price);
                printf("Quantity: %d\n", warehouse[index].quantity);
            }
            else
            {
                printf("Product not found.\n");
            }
        }
        else if (choice == 4)
        {
            int searchedCode;
            int index;
            float newPrice;

            printf("Enter code: ");
            scanf("%d", &searchedCode);

            index = findProductIndex(warehouse, count, searchedCode);

            if (index != -1)
            {
                printf("New price: ");
                scanf("%f", &newPrice);
                warehouse[index].price = newPrice;
                printf("Price updated.\n");
            }
            else
            {
                printf("Product not found.\n");
            }
        }
        else if (choice == 5)
        {
            int searchedCode;
            int index;
            int newQuantity;

            printf("Enter code: ");
            scanf("%d", &searchedCode);

            index = findProductIndex(warehouse, count, searchedCode);

            if (index != -1)
            {
                printf("New quantity: ");
                scanf("%d", &newQuantity);
                warehouse[index].quantity = newQuantity;
                printf("Quantity updated.\n");
            }
            else
            {
                printf("Product not found.\n");
            }
        }
        else if (choice == 6)
        {
            int searchedCode;
            int index;
            int soldQuantity;

            printf("Enter code: ");
            scanf("%d", &searchedCode);

            index = findProductIndex(warehouse, count, searchedCode);

            if (index != -1)
            {
                printf("Quantity to sell: ");
                scanf("%d", &soldQuantity);

                if (soldQuantity > 0 && soldQuantity <= warehouse[index].quantity)
                {
                    warehouse[index].quantity = warehouse[index].quantity - soldQuantity;
                    printf("Sale completed.\n");
                }
                else
                {
                    printf("Insufficient quantity.\n");
                }
            }
            else
            {
                printf("Product not found.\n");
            }
        }
        else if (choice == 7)
        {
            int searchedCode;
            int index;
            int addedQuantity;

            printf("Enter code: ");
            scanf("%d", &searchedCode);

            index = findProductIndex(warehouse, count, searchedCode);

            if (index != -1)
            {
                printf("Quantity to add: ");
                scanf("%d", &addedQuantity);

                if (addedQuantity > 0)
                {
                    warehouse[index].quantity = warehouse[index].quantity + addedQuantity;
                    printf("Stock updated.\n");
                }
                else
                {
                    printf("Invalid quantity.\n");
                }
            }
            else
            {
                printf("Product not found.\n");
            }
        }
        else if (choice == 8)
        {
            int i;
            float totalValue = 0;

            for (i = 0; i < count; i++)
            {
                totalValue = totalValue + warehouse[i].price * warehouse[i].quantity;
            }

            printf("Total warehouse value: %.2f\n", totalValue);
        }

    } while (choice != 9);

    return 0;
}