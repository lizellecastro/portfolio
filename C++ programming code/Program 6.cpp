/* Program 6 created by Lizelle Castro
   Submitted on 11/26/2022
   This program is intended to use a simple linked list class with only two member functions and default constructor such as
   void add(double x), boolean isMember(double x), and LinkedList(). The add function adds a new node containing x to the front
   (head) of the list, while the isMember function tests to see if the list contains a node with the value x. 
   The List Copy Constructor, Print function, the void remove(double x), and ~LinkedList() (destructor) are used as well.
   Overall this program is supposed to allowing adding, removing, and printing items of the list as well as reversing the list. 
*/


#include<iostream>
using namespace std;

struct ListNode { // struct is used to allow further access of list items using ListNode in LinkedList class
	double item;
	ListNode* next;
};

class LinkedList { // LinkedList class containing all functions for this program
private:
	ListNode* head; // initializes the ListNode starting with the head;
public:
	LinkedList() { // constructor
		head = NULL; // default head is NULL
	}
	LinkedList(LinkedList& copy) { // copy constructor
		head = copy.head; 
	}
	void add(double x) { // add function adds a new node containing x to the front of the list
		ListNode* newNode; 
		newNode = new ListNode;
		newNode->item = x; // x is in item
		newNode->next = NULL;

		if (head == NULL) {
			head = newNode;
		}
		else {
			newNode->next = head;
			head = newNode;
		}
	}
	bool isMember(double x) { // checks if member is in list by returning whether is true or false (mine compiles as 1 as true and 0 as false)
		bool flag = false;
		ListNode* temp = head;
		while (temp != NULL) {
			if (temp->item == x) { // if true, the while loop breaks
				flag = true;
				break;
			}
			temp = temp->next; // if false, temp becomes next member in list
		}
		return flag;
	}
	void reverse() { // function that reverses the order in list of items
		ListNode* temp = head;
		ListNode* t1 = NULL;
		while (temp != NULL) {
			ListNode* newNode = new ListNode;
			newNode->item = temp->item;
			newNode->next = t1;
			t1 = newNode;
			temp = temp->next;
		}
		free(head); // frees memory for head
		head = temp; // head becomes temp
	}

	void remove(double x) {  // function removes item from list
		ListNode* del; 
		if (head == NULL) {
			return;
		}
		if (head->item == x) {
			del = head;
			head = head->next;
			return;
		}
		ListNode* p = head;
		while (p->next != NULL && p->next->item != x) {
			p = p->next;
		}
		p->next == NULL;
		if (p->next == NULL) {
			return;
		}
		else {
			del = p->next;
			p->next = del->next;
			delete del;
		}
	}
	void print() { // prints the resulting list of items
		ListNode* temp = head; // ListNode object temp is assigned as head
		while (temp != NULL) {
			cout << temp->item << " ";
			temp = temp->next;
		}
		cout << endl;
	}
	~LinkedList() { // destructor, deletes head
		delete head;
	}

};

int main() {		// Testing LinkedList class by adding, removing, printing, and whether selected item is in list or not.
	LinkedList list;
	list.add(10);
	list.remove(10);
	list.add(2);
	list.add(8);
	list.add(9);

	cout << "Numbers displayed: " << endl;
	list.print();

	cout << "Is member 1 in the list? " << list.isMember(1)<<endl;
/*	if (list.isMember(1) == true) {
		cout << "true" << endl;
	}
	else {
		cout << "false" << endl;
	}
*/
	cout << "Is member 9 in the list? " << list.isMember(9)<< endl;

	return 0;
}