/***********************************************************************************************************
* COSC 2420-001
* Project #5 - Spring 2023
* Created by Lizelle Castro
* Due Date: 4/10/2023
* Purpose:	This program is intended to take values for 3 cars by the user as the number of hours to 
*			calculate the charge for each car and the total of charges overall as well as the total number
*			of hours overall. Only values from 0 to 24 for each car is allowed when the user is prompted
*			to enter the 3 values. Afterwards, the hour and charge for each car is displayed in tabular 
*			format and on the last row are the totals for hours and charges.
************************************************************************************************************/

#include<stdio.h>
#include<math.h>
#include <stdbool.h> // used for boolean data type

float calculateCharges(float);

void main() {
	float car1, car2, car3;

	printf("Enter the hours parked for 3 cars (separated in spaces): \n");
	scanf_s("%f %f %f", &car1, &car2, &car3);

	bool flag = true; // used to determine when the while loop ends

	// This checks whether the values input by the user are 
	// valid and allows to re-enter up until the values are valid
	if (car1 > 24 || car2 > 24 || car3 > 24 || car1 < 0 || car2 < 0 || car3 < 0) {
		while (flag == true) {
			if (car1 > 24 || car2 > 24 || car3 > 24 || car1 < 0 || car2 < 0 || car3 < 0) {
				printf("Re-enter the hours parked for 3 cars (separated by spaces): \n");
				scanf_s("%f %f %f", &car1, &car2, &car3);
			}
			else
				flag = false;
		}
	}

	float arr[] = { car1, car2, car3 }; // to faciliate moving on from the first car value to the next
	float charge;
	float total_hr = 0.00;
	float total_charge = 0.00;

	printf("Car	    Hours	   Charge \n");

	// for loop calls calculateCharges function for each car 
	// and sums the total hours and total charges
	for (int i = 0; i < 3; i++) {
		charge = calculateCharges(arr[i]);
		printf("%d	    %.2f	   %.2f \n", i + 1, arr[i], charge);
		total_hr = total_hr + arr[i];
		total_charge = total_charge + charge;
	}
	printf("Total	    %.2f	   %.2f \n", total_hr, total_charge);
}

// Calculates charge for each customer
float calculateCharges(float hours) {
	float amount;
	if (hours == 0.0) { // exactly 0 hours is no charge
		amount = 0.00;
	}
	else if (hours <= 3) {     // $2.00 minimum fee to park for up to 3 hours
		amount = 2.00;
	}
	else if (hours > 3 && hours < 24) {				// charge an additional $0.50 per hour if past 3 hours
		amount = 2.00 + ((hours - 3.00) * 0.50);    // and less than 24 hours
	}
	else {			// if hours is exactly 24 then the charge is $10.00
		amount = 10.00;
	}
	return amount;
}
