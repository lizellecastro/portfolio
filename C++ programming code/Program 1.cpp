// Program 1 created by Lizelle Castro
// Submitted on 10/20/2022
/* This program prompts the user to enter the total number of scores then the user will be asked to enter the name of a student
   and its corresponding score depending on what the user entered as the total number of scores to be stored in the arrays.
   The program will sort the name-score pairs in ascending order based on the scores entered. Finally, after the sorted list of
   name-score pairs are displayed then the program will calculate the average score and display it as well. */

#include <iostream>
#include <string>
using namespace std;

typedef struct Student {	//struct named Student with members that will be accessed throughout the program
	string name;
	int score;
};

void bubble_sort(Student* students, int total) {	//function that uses bubble sort algorithm

	for (int i = 1; i < total; i++) {
		for (int j = 0; j < total - i; j++) {	//In traversing the array, pointers are used in nested for loop
			if ((*(students + j)).score > (*(students + j + 1)).score) {  //if statement compares the scores in students
				Student temp = *(students + j);			// Student temp is used as placeholder to allow swapping of elements of name-score pairs
				*(students + j) = *(students + j + 1);
				*(students + j + 1) = temp;
			}
		}
	}
}


float AverageScore(Student* students, int numScores) { //function that calculates average score
	float totalsum = 0.0;				 //initializes sum of scores
	int totalnum = numScores;			 //total number of scores to be calculated

	for (int i = 0; i < sizeof(students); i++) {
		totalsum = totalsum + (*(students + i)).score; //adds each score of array as for loop progresses
	}
	float average = totalsum / totalnum; // average score calculated

	return average;
}

// Program starts

int main() {
	Student* students; //declaring an array of pointers of struct Student
	int numScores;
	int exclude_score = 0; //initializes number of scores to exclude from calculating average score

	//// User will enter total number of scores

	cout << "Enter total positive number of scores: " << endl;
	cin >> numScores;

	students = new Student[numScores]; //the students array now has size entered by user

	//// User will be prompted to enter student name and test score depending on how many total number of scores was inputed

	for (int i = 0; i < numScores; i++) {
		Student name_score_array; //An array of struct Student to store name and score

		cout << "Enter student #" << i + 1 << " name and test score(no score less than 0) followed by pressing Enter : " << endl;
		cin >> name_score_array.name >> name_score_array.score;

		if (name_score_array.score < 0) {   //this makes sure the negative scores are not stored in the students array
			cout << "invalid score" << endl;
			exclude_score++; //counts the number of scores by 1 to exclude from calculating average score
		}
		else {
			*(students + i) = name_score_array; //updating name and score to students array
		}

	}
	cout << endl;

	bubble_sort(students, numScores); // This calls the bubble_sort function to sort the array

	//// The sorted name-score pairs will be displayed.

	cout << "The list of sorted name-score pairs: " << endl;

	for (int i = 0; i < numScores; i++) {
		if ((*(students + i)).name != "") { //this excludes the name-score pairs with names that were not stored and had score less than 0
			cout << (*(students + i)).name << " " << (*(students + i)).score << endl;
		}
	}
	cout << endl;

	int New_numScores = numScores - exclude_score; // Any scores entered that were less than zero are excluded from the total number of scores

	//// Finally, the AverageScore function will be called and display the average score

	cout << "The average score is: " << AverageScore(students, New_numScores) << endl;

	delete[] students; // to deallocate dynamically created students array
	
	// Program ends

	return 0;
}