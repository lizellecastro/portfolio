/* Created by Lizelle Castro
 * COSC 3420.501
 * Project #1
 * Due date: 2/9/2023
 * Purpose: This program is intended for the user to enter a date in the format of mm-dd-yyyy
 *          and display the next date and prior date of the date entered. Then at the end it 
 *          displays the day of the week on which the date entered falls on.
 */

import java.util.*;
import java.util.Calendar; // Calendar class is an abstract class only used in the getDayOfWeek method
import java.util.Scanner; // Scanner class used to take user input in main method

// CalendarDate Class

public class CalendarDate {
    private int month;
    private int day;
    private int year;
    
    public CalendarDate() { // CalendarDate default constructor
        month = 1;
        day = 1;
        year = 2012;
    }
    public CalendarDate(int Month, int Day, int Year) { // CalendarDate constructor
        if(!isValid(Month, Day, Year)) { // checks if the input is not valid before assigning the date
            month = 1;
            day = 1;
            year = 2012;
        }
        else {
            month = Month;
            day = Day;
            year = Year;
        }
    }
    public boolean isValid(int Month, int Day, int Year) { // isValid used to check if input is valid
        boolean flag = false; 
        boolean Feb_flag = false; // specific for checking the validity of date when the month is 2
        switch(Month) { // checks if day is valid for month that was entered
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
            if(Day > 0 && Day <= 31) {
                flag = true;
            }
            break;
            case 2:
            case 4:
            case 6:
            case 9:
            case 11:
            if(Day > 0 && Day <= 30) {
                flag = true;
            }
            break;
        }
        if(Day == 29 && Month == 2) { // checks only for February including day and year and if it is a leap year
            Feb_flag = isLeapYear(Year);
            if(Feb_flag == false) {
                flag = false;
            }
        }
        if(Year < 2012) {
            flag = false;
        }
        return flag;
    }
    private boolean isLeapYear(int Year) { // isLeapYear checks if year is a leap year
        if(Year % 4 == 0 && Year % 100 == 0 && Year % 400 == 0) {
            return true;
        }
        else if(Year % 4 == 0 && Year % 100 == 0 && Year % 400 != 0){
            return false;
        }
        else if(Year % 4 == 0 && Year % 100 != 0) {
            return true;
        }
        else {
            return false;
        }
    }
    private int getNumDays(int Month, int Year) { // getNumDays returns the total number of days for the month entered
        int numDay = 0;
        int[] arr_31 = new int[] {1,3,5,7,8,10,12}; // array of months that has 31 days
        int[] arr_30 = new int[] {4,6,9,11}; // array of months that has 30 days
        for(int i = 0; i < arr_31.length; i++) { 
            if(arr_31[i] == Month) {
                numDay = 31;
            }
        }
        for(int j = 0; j < arr_30.length; j++) {
            if(arr_30[j] == Month) {
                numDay = 30;
            }
        }

        if(Month == 2) { // depending if it is leap year, february will have either 28 or 29 days
            if(isLeapYear(Year)) {
                numDay = 29;
            }
            else {
                numDay = 28;
            }
        }
        return numDay;
    }
    private String getMonth(int Month) { // getMonth returns a string of exact name of month
        String monthName = " ";
        switch(Month) {
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;
            case 11: 
                monthName = "November";
                break;
            case 12:
                monthName = "December";
        }
        return monthName;
    }
    public String getNextDate() { // getNextDate returns the exact next date of date that was entered
        int nextDate = 0, Month = 0, Year = 0;
        int numDays = getNumDays(month, year);
        if((day+1) <= numDays) {
            Month = month;
            Year = year;
            nextDate = day + 1;
        }
        else if((day+1) > numDays && month+1 > 12) {
            Month = 1;
            Year = year + 1;
            nextDate = 1;
        }
        else if((day+1) > numDays && month+1 <= 12) {
            Month = month + 1;
            Year = year;
            nextDate = 1;
        }
        String MonthName = getMonth(Month);
        return MonthName + " " + nextDate + ", " + Year + ".";
    }
    public String getPrevDate() { // getPrevDate returns the exact prior date of date that was entered
        int prevDay, Month, Year;
        int temp;
        if(day == 1 && month - 1 < 1) {
            prevDay = 31;
            Month = 12;
            Year = year - 1;
        }
        else if(day == 1 && month - 1 >= 1) {
            temp = getNumDays(month - 1, year);
            prevDay = temp;
            Month = month - 1;
            Year = year;
        }
        else {
            prevDay = day - 1;
            Month = month;
            Year = year;
        }
        String monthName = getMonth(Month);
        return monthName + " " + prevDay + ", " + Year + ".";
    }
    public String getDayOfWeek() { // getDayOfWeek returns the day of the week the date entered falls on
        Calendar obj = new GregorianCalendar(year, month - 1, day); // GregorianCalendar class is a subclass of Calendar class, 
                                                                    // the Calendar object is initialized and assigned with the set 
                                                                    // date created through the GregorianCalendar constructor
        int n = obj.get(Calendar.DAY_OF_WEEK);      // the Calendar object obtains the day of week field from the Calendar class and is assigned to n as integer
        String weekDay = " ";
        switch(n) {
            case 1:
                weekDay = "Sunday.";
                break;
            case 2:
                weekDay = "Monday.";
                break;
            case 3:
                weekDay = "Tuesday.";
                break;
            case 4:
                weekDay = "Wednesday.";
                break;
            case 5:
                weekDay = "Thursday.";
                break;
            case 6:
                weekDay = "Friday.";
                break;
            case 7:
                weekDay = "Saturday.";
                break;
        }
        return weekDay;
    }

    // main method to run program
    public static void main(String[] args) {
        int Month, Day, Year;
        Scanner obj = new Scanner(System.in);
        CalendarDate date;

        System.out.print("Please enter a date using the following format mm dd yyyy: ");

        Month = obj.nextInt();
        Day = obj.nextInt();
        Year = obj.nextInt();

        date = new CalendarDate(Month, Day, Year);

        System.out.println("The next date is " + date.getNextDate());
        System.out.println("The prior date is " + date.getPrevDate());
        System.out.println("The day of the week is " + date.getDayOfWeek());
        
        System.out.println();

        obj.close(); // closes Scanner object, removes resource leak warning
    }
}
