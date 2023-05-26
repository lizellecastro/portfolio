/*****************************************************************************************
* COSC 2420-001
* Project 4 - Spring 2023
* Created by Lizelle Castro
* Due Date: 03/22/2023
* Purpose: This program is intended to take an odd number from 1-19 input by the user and
*		   display a diamond of asterisks based on the input as the size of the diamond.
******************************************************************************************/

#include<stdio.h>

void main() {
	int num;
	// user input
	printf("Enter an odd number for the diamond size(1-19): \n");
	scanf_s("%d", &num);

	// checks to ensure the input is an odd number from 1 - 19
	while (num % 2 == 0 || num < 1 || num > 19) {
		printf("Invalid input - please enter an odd number for the diamond size(1-19): \n");
		scanf_s("%d", &num);
	}
	
	int i, j, k;
	// top diamond including middle row of asterisks
	for (i = 0; i <= num / 2; i++) {
		for (j = 0; j < (num / 2) - i; j++) {
			printf(" ");
		}
		for (k = 0; k < (i * 2) + 1; k++) {
			printf("*");
		}
		printf("\n");
	}
	
	// bottom diamond
	for (i = 0; i < num / 2; i++) {
		for (j = 0; j < i + 1; j++) {
			printf(" ");
		}
		for (k = 0; k < num - (i * 2) - 2; k++) {
			printf("*");
		}
		printf("\n");
	}
	
}