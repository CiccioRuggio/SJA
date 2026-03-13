#include <stdio.h>
#include <stdlib.h>

#define MAX_PRODUCTS 100

void printWelcomeMessage(char programName[])
{
    printf("Welcome to the %s program!\nPress ENTER to continue...", programName);
    getchar();
    printf("\n\n\n");
}

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

/* Reads an integer in [min, max]; clears buffer on bad input and retries */
int read_int_in_range(int min, int max)
{
    int value;
    int valid = 0;

    while (!valid)
    {
        if (scanf("%d", &value) == 1)
        {
            if (value >= min && value <= max)
                valid = 1;
            else
                printf("Value must be between %d and %d. Try again: ", min, max);
        }
        else
        {
            printf("Invalid input. Try again: ");
            while (getchar() != '\n' && getchar() != EOF);
        }
    }
    return value;
}

/* Adds a new product or increases quantity if code already exists */
void add_or_update(int codes[], int quantities[], int *count, int max)
{
    int code, qty, idx;

    printf("Enter product code: ");
    code = read_int_in_range(1, 999999);

    printf("Enter initial quantity: ");
    qty = read_int_in_range(0, 999999);

    idx = find_index(codes, *count, code);

    if (idx != -1)
    {
        quantities[idx] += qty;
        printf("Product already exists. Updated quantity: %d\n", quantities[idx]);
    }
    else if (*count >= max)
    {
        printf("Error: warehouse is full (%d products max).\n", max);
    }
    else
    {
        codes[*count] = code;
        quantities[*count] = qty;
        (*count)++;
        printf("Product added successfully.\n");
    }
}

/* Applies a positive or negative delta to the quantity of a product */
void apply_delta(int codes[], int quantities[], int count)
{
    int code, delta, idx;

    printf("Enter product code: ");
    code = read_int_in_range(1, 999999);

    idx = find_index(codes, count, code);

    if (idx == -1)
    {
        printf("Product not found.\n");
        return;
    }

    printf("Enter delta (positive = load, negative = unload): ");
    delta = read_int_in_range(-999999, 999999);

    if (quantities[idx] + delta < 0)
    {
        printf("Error: operation would result in negative quantity (current: %d).\n", quantities[idx]);
    }
    else
    {
        quantities[idx] += delta;
        printf("Updated quantity for code %d: %d\n", code, quantities[idx]);
    }
}

/* Searches for a product by code and prints its index and quantity */
void search(int codes[], int quantities[], int count)
{
    int code, idx;

    printf("Enter product code to search: ");
    code = read_int_in_range(1, 999999);

    idx = find_index(codes, count, code);

    if (idx == -1)
        printf("Product not found.\n");
    else
        printf("Found at index %d | Code: %d | Quantity: %d\n", idx, codes[idx], quantities[idx]);
}

/* Prints the main menu */
void print_menu(void)
{
    printf("=== WAREHOUSE MENU ===\n");
    printf("1. Add product\n");
    printf("2. Load / Unload\n");
    printf("3. Search product\n");
    printf("4. Report\n");
    printf("5. Exit\n");
    printf("Choose (1-5): ");
}

/* Prints all products and the total quantity in the warehouse */
void print_report(int codes[], int quantities[], int count)
{
    int i, total = 0;

    if (count == 0)
    {
        printf("Warehouse is empty.\n");
        return;
    }

    printf("%-10s %-10s\n", "Code", "Quantity");
    printf("---------------------\n");

    for (i = 0; i < count; i++)
    {
        printf("%-10d %-10d\n", codes[i], quantities[i]);
        total += quantities[i];
    }

    printf("---------------------\n");
    printf("Total items: %d\n", total);
}

int main(void)
{
    int codes[MAX_PRODUCTS];
    int quantities[MAX_PRODUCTS];
    int count = 0;
    int choice;

    printWelcomeMessage("Warehouse Management System");
    system("clear");

    do
    {
        print_menu();

        choice = read_int_in_range(1, 5);
        printf("\n");

        switch (choice)
        {
            case 1:
                add_or_update(codes, quantities, &count, MAX_PRODUCTS);
                break;
            case 2:
                apply_delta(codes, quantities, count);
                break;
            case 3:
                search(codes, quantities, count);
                break;
            case 4:
                print_report(codes, quantities, count);
                break;
            case 5:
                printf("Goodbye!\n");
                break;
        }

        printf("\n");

    } while (choice != 5);

    return 0;
}
