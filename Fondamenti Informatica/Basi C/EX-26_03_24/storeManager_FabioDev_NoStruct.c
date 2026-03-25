#include <stdio.h>
#include <string.h>
#define MAX_PRODUCTS 100
#define NAME_LENGTH 30
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
int findProductIndex(int codes[], int count, int searchedCode)
{
    int i;
    for (i = 0; i < count; i++)
    {
        if (codes[i] == searchedCode)
        {
            return i;
        }
    }
    return -1;
}
void insertProduct(int codes[], char names[][NAME_LENGTH], float prices[], int quantities[], int position)
{
    printf("Code: ");
    scanf("%d", &codes[position]);
    printf("Name: ");
    scanf("%s", names[position]);
    printf("Price: ");
    scanf("%f", &prices[position]);
    printf("Quantity: ");
    scanf("%d", &quantities[position]);
}
void showProducts(int codes[], char names[][NAME_LENGTH], float prices[], int quantities[], int count)
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
                   codes[i], names[i], prices[i], quantities[i]);
        }
    }
}
int main()
{
    int codes[MAX_PRODUCTS];
    char names[MAX_PRODUCTS][NAME_LENGTH];
    float prices[MAX_PRODUCTS];
    int quantities[MAX_PRODUCTS];

    int count = 0;
    int choice;
    do
    {
        choice = showMenu();
        if (choice == 1)
        {
            if (count < MAX_PRODUCTS)
            {
                insertProduct(codes, names, prices, quantities, count);
                count++;
            }
            else
            {
                printf("Archive full.\n");
            }
        }
        else if (choice == 2)
        {
            showProducts(codes, names, prices, quantities, count);
        }
        else if (choice == 3)
        {
            int searchedCode;
            int index;
            printf("Enter code: ");
            scanf("%d", &searchedCode);
            index = findProductIndex(codes, count, searchedCode);
            if (index != -1)
            {
                printf("Code: %d\n", codes[index]);
                printf("Name: %s\n", names[index]);
                printf("Price: %.2f\n", prices[index]);
                printf("Quantity: %d\n", quantities[index]);
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
            index = findProductIndex(codes, count, searchedCode);
            if (index != -1)
            {
                printf("New price: ");
                scanf("%f", &newPrice);
                prices[index] = newPrice;
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
            index = findProductIndex(codes, count, searchedCode);
            if (index != -1)
            {
                printf("New quantity: ");
                scanf("%d", &newQuantity);
                quantities[index] = newQuantity;
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
            index = findProductIndex(codes, count, searchedCode);
            if (index != -1)
            {
                printf("Quantity to sell: ");
                scanf("%d", &soldQuantity);
                if (soldQuantity > 0 && soldQuantity <= quantities[index])
                {
                    quantities[index] = quantities[index] - soldQuantity;
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
            index = findProductIndex(codes, count, searchedCode);
            if (index != -1)
            {
                printf("Quantity to add: ");
                scanf("%d", &addedQuantity);
                if (addedQuantity > 0)
                {
                    quantities[index] = quantities[index] + addedQuantity;
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
                totalValue = totalValue + prices[i] * quantities[i];
            }
            printf("Total warehouse value: %.2f\n", totalValue);
        }
    } while (choice != 9);
    return 0;
}