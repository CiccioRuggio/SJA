#include "../Lib/mainFunc.h"
#include <string.h>
#include <limits.h>

#define MAX_PRODUCTS 100
#define NAME_LENGTH  30

/* Returns the index of the product with the given code, or -1 if not found */
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

/* Inserts a new product; if the code already exists, only increases the quantity */
void insert_product(int codes[], char names[][NAME_LENGTH], float prices[],
                    int quantities[], int *count, int max)
{
    int  code, qty, idx;
    char name[NAME_LENGTH];
    float price;

    code = read_int_in_range("Enter product code", 1, INT_MAX);
    idx  = find_index(codes, *count, code);

    if (idx != -1)
    {
        qty = read_int_in_range("Code already exists. Enter quantity to add", 1, 1000);
        quantities[idx] += qty;
        printf("Quantity updated: %d\n", quantities[idx]);
        return;
    }

    if (*count >= max)
    {
        printf("Error: warehouse is full (%d products max).\n", max);
        return;
    }

    printf("Enter product name: ");
    scanf("%29s", name);
    strncpy(names[*count], name, NAME_LENGTH - 1);
    names[*count][NAME_LENGTH - 1] = '\0';

    printf("Enter unit price: ");
    scanf("%f", &price);

    qty = read_int_in_range("Enter initial quantity", 0, 1000);

    codes[*count]      = code;
    prices[*count]     = price;
    quantities[*count] = qty;
    (*count)++;

    printf("Product added successfully.\n");
}

/* Searches for a product by code and prints all its data */
void search(int codes[], char names[][NAME_LENGTH], float prices[],
            int quantities[], int count)
{
    int code, idx;

    code = read_int_in_range("Enter product code to search", 1, INT_MAX);
    idx  = find_index(codes, count, code);

    if (idx == -1)
    {
        printf("Product not found.\n");
        return;
    }

    printf("Code     : %d\n",   codes[idx]);
    printf("Name     : %s\n",   names[idx]);
    printf("Price    : %.2f\n", prices[idx]);
    printf("Quantity : %d\n",   quantities[idx]);
}

/* Updates the unit price of an existing product */
void update_price(int codes[], float prices[], int count)
{
    int   code, idx;
    float new_price;

    code = read_int_in_range("Enter product code", 1, INT_MAX);
    idx  = find_index(codes, count, code);

    if (idx == -1)
    {
        printf("Product not found.\n");
        return;
    }

    printf("Enter new price: ");
    scanf("%f", &new_price);

    if (new_price < 0)
    {
        printf("Error: price cannot be negative.\n");
        return;
    }

    prices[idx] = new_price;
    printf("Price updated: %.2f\n", prices[idx]);
}

/* Replaces the quantity of an existing product with a new absolute value */
void update_quantity(int codes[], int quantities[], int count)
{
    int code, idx, new_qty;

    code = read_int_in_range("Enter product code", 1, INT_MAX);
    idx  = find_index(codes, count, code);

    if (idx == -1)
    {
        printf("Product not found.\n");
        return;
    }

    new_qty = read_int_in_range("Enter new quantity", 0, 1000);
    quantities[idx] = new_qty;
    printf("Quantity updated: %d\n", quantities[idx]);
}

/* Reduces the quantity of a product (sale); fails if stock is insufficient */
void sell_product(int codes[], int quantities[], int count)
{
    int code, idx, qty_sold;

    code = read_int_in_range("Enter product code", 1, INT_MAX);
    idx  = find_index(codes, count, code);

    if (idx == -1)
    {
        printf("Product not found.\n");
        return;
    }

    qty_sold = read_int_in_range("Enter quantity to sell", 1, quantities[idx]);

    if (qty_sold > quantities[idx])
    {
        printf("Error: insufficient stock (available: %d).\n", quantities[idx]);
        return;
    }

    quantities[idx] -= qty_sold;
    printf("Sale completed. Remaining quantity: %d\n", quantities[idx]);
}

/* Increases the quantity of an existing product (stock loading) */
void add_stock(int codes[], int quantities[], int count)
{
    int code, idx, qty_added;

    code = read_int_in_range("Enter product code", 1, INT_MAX);
    idx  = find_index(codes, count, code);

    if (idx == -1)
    {
        printf("Product not found.\n");
        return;
    }

    qty_added = read_int_in_range("Enter quantity to add", 1, 1000);
    quantities[idx] += qty_added;
    printf("Stock updated. New quantity: %d\n", quantities[idx]);
}

/* Prints all products in a formatted table */
void print_report(int codes[], char names[][NAME_LENGTH], float prices[],
                  int quantities[], int count)
{
    int i;

    if (count == 0)
    {
        printf("Warehouse is empty.\n");
        return;
    }

    printf("%-10s %-30s %-10s %-10s\n", "Code", "Name", "Price", "Quantity");
    printf("-------------------------------------------------------------\n");

    for (i = 0; i < count; i++)
    {
        printf("%-10d %-30s %-10.2f %-10d\n",
               codes[i], names[i], prices[i], quantities[i]);
    }
}

/* Calculates and prints the total value of the warehouse (price * quantity) */
void calc_warehouse_value(float prices[], int quantities[], int count)
{
    int   i;
    float total = 0.0f;

    for (i = 0; i < count; i++)
        total += prices[i] * quantities[i];

    printf("Total warehouse value: %.2f\n", total);
}

/* Prints the main menu */
void print_menu(void)
{
    printf("=== WAREHOUSE MENU ===\n");
    printf("1. Insert product\n");
    printf("2. Show all products\n");
    printf("3. Search product\n");
    printf("4. Update price\n");
    printf("5. Update quantity\n");
    printf("6. Sell product\n");
    printf("7. Add stock\n");
    printf("8. Calculate warehouse value\n");
    printf("0. Exit\n");
}

int main()
{
    int   codes[MAX_PRODUCTS];
    char  names[MAX_PRODUCTS][NAME_LENGTH];
    float prices[MAX_PRODUCTS];
    int   quantities[MAX_PRODUCTS];
    int   count = 0;
    int   choice;

    printWelcomeMessage("Warehouse Management System");
    system("clear");

    do
    {
        print_menu();
        choice = read_int_in_range("Choose", 0, 8);
        printf("\n");

        switch (choice)
        {
            case 1:
                insert_product(codes, names, prices, quantities, &count, MAX_PRODUCTS);
                break;
            case 2:
                print_report(codes, names, prices, quantities, count);
                break;
            case 3:
                search(codes, names, prices, quantities, count);
                break;
            case 4:
                update_price(codes, prices, count);
                break;
            case 5:
                update_quantity(codes, quantities, count);
                break;
            case 6:
                sell_product(codes, quantities, count);
                break;
            case 7:
                add_stock(codes, quantities, count);
                break;
            case 8:
                calc_warehouse_value(prices, quantities, count);
                break;
            case 0:
                printf("Goodbye!\n");
                break;
        }

        printf("\n");

    } while (choice != 0);

    return 0;
}
