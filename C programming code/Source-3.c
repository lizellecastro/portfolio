/****************************************************************************************************
* COSC 2420-001
* Project #6 - Spring 2023
* Created by Lizelle Castro
* Due date: 04/19/2023
* Purpose:		This program is intended to assign seats of the airline's only plane of a 
*				capacity of 10 seats. It asks the user to assign a seat for either the 
*				first class or economy section. The program will keep track of the seats
*				being filled and if a section is full the user is asked whether to switch sections
*				of available seats or not. If both sections are full then the program ends with 
*				displaying "Next flight leaves in 3 hours."
****************************************************************************************************/

#include <stdio.h>
#include <stdbool.h>

void main() {
	int choice;		// class type chosen
	int ans;		// when asked yes or no
	int arr[10] = { 0,0,0,0,0,0,0,0,0,0 }; 
	bool flag0 = true;	// for while loop
	bool flag1 = false; // for first class section when full
	bool flag2 = false; // for economy section when full

	while (flag0 == true) {
		printf("Enter 1 for first class or 2 for economy: \n");
		scanf_s("%d", &choice);

		// First class section
		if (choice == 1) {		
			for (int i = 0; i < 5; i++) {
				if (arr[i] == 0) {
					arr[i] = 1;
					printf("****************Boarding Pass****************\n");
					printf("Seat number: %d\n", i + 1);
					printf("Section: First Class\n");
					printf("*********************************************\n");
					printf("\n");
					flag1 = false;
					break;
				}
				flag1 = true;	// flag1 is true if the seats are full for first class
			}
		}

		// Economy section
		else if (choice == 2) {
			for (int i = 5; i < 10; i++) {
				if (arr[i] == 0) {
					arr[i] = 1;
					printf("****************Boarding Pass****************\n");
					printf("Seat number: %d\n", i + 1);
					printf("Section: Economy\n");
					printf("*********************************************\n");
					printf("\n");
					flag2 = false;
					break;
				}
				flag2 = true; // flag2 is true if the seats are full for economy
			}
		}
		else {
			printf("Invalid input - type 1 for first class or 2 for economy");
		}

		// first class full, change section
		if (choice == 1 && flag1 == true) {
			printf("The first class section is full, is it acceptable to be placed in the economy section? (type 1 for yes or 2 for no)");
			scanf_s("%d", &ans);

			if (ans == 1) {
				for (int i = 5; i < 10; i++) {
					if (arr[i] == 0) {
						arr[i] = 1;
						printf("****************Boarding Pass****************\n");
						printf("Seat number: %d\n", i + 1);
						printf("Section: Economy\n");
						printf("*********************************************\n");
						printf("\n");
						flag2 = false;
						break;
					}
					flag2 = true; // flag2 is true if the seats are full for economy
				}
			}
			else {
				printf("\n");
				printf("Next flight leaves in 3 hours.\n");
				flag0 = false;
			}
		}

		// economy section is full, change section
		else if (choice == 2 && flag2 == true) {
			printf("The economy section is full, is it acceptable to be placed in the first class section? (type 1 for yes or 2 for no)");
			scanf_s("%d", &ans);

			if (ans == 1) {
				for (int i = 0; i < 5; i++) {
					if (arr[i] == 0) {
						arr[i] = 1;
						printf("****************Boarding Pass****************\n");
						printf("Seat number: %d\n", i + 1);
						printf("Section: First Class\n");
						printf("*********************************************\n");
						printf("\n");
						flag1 = false;
						break;
					}
					flag1 = true; // flag1 is true if the seats are full for first class
				}
			}
			else {
				printf("\n");
				printf("Next flight leaves in 3 hours.\n");
				flag0 = false;
			}
		}

		// when both sections are full
		if (flag1 == true && flag2 == true) {
			printf("\n");
			printf("Both sections are full.\n");
			printf("Next flight leaves in 3 hours.\n");
			flag0 = false;
		}

	}

}