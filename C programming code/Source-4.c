/********************************************************************************************************************
* COSC 2420-001
* Project 7 - Spring 2023
* Created by: Lizelle Castro
* Due Date: May 4, 2023
* Purpose:	This program is intended to track inventory of items of a grocery store. The program
*			uses a structure whose members represent the following: the current month, an item's name,
*			unique identification code for each item, actual quantity in stock for the current month,
*			price, and sales total in dollars for the current month. The store already has 3 items in the
*			inventory so any other item will be added by the user along with the information that will be
*			associated with that item. This program is menu-driven so it will have 6 options to choose from
*			(0 - exit, 1 - Add item, 2 - Item Sale, 3 - Update Quantity, 4 - Daily Report, 5 - Monthly Report).
*			Depending on what option the user chooses the program will execute according to its choice up until
*			0 entered to exit the program. NOTE: A maximum number of 20 items total is allowed to be in inventory.
********************************************************************************************************************/

#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#define SIZE 20		// maximum number of items allowed
#define _CRT_SECURE_NO_WARNINGS

// Items struct
struct Items {
	char month[20];
	char name[20];
	int id;
	int qty;
	float price;
	float total_sales;
};

void main() {
	int size = 3;
	struct Items item[SIZE];
	struct Items total;
	total.total_sales = 0.0;

	// initializing the first 3 items to be added to inventory
	for (int i = 0; i < size; i++) {
		strcpy_s(item[i].month, sizeof(item[0].month), "May");
		strcpy_s(item[i].name, sizeof(item[0].name), "item");
		item[i].id = i + 1;
		item[i].qty = 10;
		item[i].price = 1.0;
	}

	int choice = -1;
	int id_num = 0;
	int qty = 0;
	char month[] = "                    ";
	char food[] = "                    ";
	bool flag = false;

	while (choice != 0) {
		printf("Choose option 1 - Add Item, 2 - Item Sale, 3 - Update Quantity, 4 - Daily Report, 5 - Monthly Report, or 0 - Exit: \n");
		scanf_s("%d", &choice);
		getchar();

		// Adds item and updates for all associated struct members
		if (choice == 1) {
			size++;
			printf("Enter the current month: \n");
			fgets(item[size - 1].month, SIZE, stdin);

			printf("Enter the new item name: \n");
			scanf_s("%s", food, SIZE);
			getchar();

			strcpy_s(item[size - 1].name, SIZE, food);

			printf("Enter new identification number for this item: \n");
			scanf_s("%d", &item[size - 1].id);
			printf("Enter the quantity of this item: \n");
			scanf_s("%d", &item[size - 1].qty);
			printf("Enter the price of this item: \n");
			scanf_s("%f", &item[size - 1].price);
			total.total_sales = 0.0;
		}

		// updates quantity of item sold and total sales
		else if (choice == 2) {
			printf("Enter the id number of the item sold: \n");
			scanf_s("%d", &id_num);
			printf("Enter the quantity of this item sold: \n");
			scanf_s("%d", &qty);

			if (size == 0) {
				printf("No items in store.\n");
			}
			else {
				for (int i = 0; i < size; i++) {
					if (item[i].id == id_num) {
						item[i].qty = item[i].qty - qty;
						total.total_sales = total.total_sales + (item[i].price * qty);
						flag = true;
						break;
					}
				}
				if (flag == false) {
					printf("Item not found.\n");
				}
			}

		}

		// updates quantity of specific item based on item id
		else if (choice == 3) {
			printf("Enter the id number of the item to update quantity: \n");
			scanf_s("%d", &id_num);
			printf("Enter the new quantity of this item: \n");
			scanf_s("%d", &qty);

			for (int i = 0; i < size; i++) {
				if (item[i].id == id_num) {
					item[i].qty = qty;
				}
			}
		}

		// prints out daily report and current total sales
		else if (choice == 4) {
			printf("Item Daily Report: \n");
			printf("Item ID		Quantity\n");
			for (int i = 0; i < size; i++) {
				printf("%d		%d\n", item[i].id, item[i].qty);
			}
			printf("Sales Total: %f\n", total.total_sales);
		}

		// prints out monthly report that displays the quantity in stock for all items
		else if (choice == 5) {
			printf("Enter the current month: \n");
			fgets(month, sizeof(month), stdin);
			//getchar();
			printf("%s Monthly Report: \n", month);
			printf("Item ID		Item Name	Quantity\n");
			for (int i = 0; i < size; i++) {
				printf("%d		%s		%d\n", item[i].id, item[i].name, item[i].qty);
			}
		}
	}
}