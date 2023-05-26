/******************************************************************************************************
 * Created by Lizelle Castro
 * COSC 3420.501
 * Project #2
 * Due date: 2/28/2023
 * Purpose: This program is intended to demonstrate that Marilyn vos Savant gave the right 
 *          advice that the person playing in the game show, "Let's make a deal" should always
 *          switch after their initial guess. This program takes the number of tries as 
 *          input then generates a list of possible scenarios of numbers between 1-3 as output 
 *          for what would be the prize, guess, view (opened by the game host that does not have the 
 *          prize), and new guess (the door that was switched to after the initial guess). 
 *          Then probability of winning by switching and not switching are calculated and displayed.
 ******************************************************************************************************/
import java.util.*;
import java.util.Scanner;

public class MakeADeal {
    public static void main(String[] args) {
        int tries;
        double count_win = 0; // number of wins by switching
        Scanner obj = new Scanner(System.in);

        System.out.println("Enter the number of times you want to play: ");
        tries = obj.nextInt(); // user input

        Random rand = new Random(); // Random class object initialized
        int prize, guess, view, newGuess;

        System.out.println("Prize   Guess   View    New Guess");

        for(int i = 0; i < tries; i++) {    // loops through the number of tries by user

            // randomized numbers called by Random method nextInt() for each variable
            prize = rand.nextInt(3) + 1;
            guess = rand.nextInt(3) + 1;
            view = rand.nextInt(3) + 1;
            
            // makes sure the view is not the same as prize and guess
            while(view == prize || view == guess) {
                view = rand.nextInt(3) + 1;
            }
            
            // new guess after initial guess
            newGuess = rand.nextInt(3) + 1;

            // makes sure the new guess is not the same as guess and view
            while(newGuess == guess || newGuess == view) {
                newGuess = rand.nextInt(3) + 1;
            }

            System.out.println(prize + "        " + guess + "       " + view + "        " + newGuess);

            // tracks the number of times the new guess matches the prize
            if(prize == newGuess) {
                count_win++;
            }
        }

        // probability of winning by switching and not switching
        double to_switch, not_switch;
        to_switch = count_win/tries;
        not_switch = 1 - to_switch;

        System.out.printf("Probability of winning if you switch = %.2f\n", to_switch);
        System.out.printf("Probability of winning if you do not switch = %.2f\n", not_switch);

        obj.close(); // closes Scanner object, removes resource leak warning
    }
}