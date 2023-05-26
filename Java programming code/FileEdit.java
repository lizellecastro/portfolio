/****************************************************************************************************
 * Created by Lizelle Castro
 * COSC 3420.501
 * Project #3
 * Due date: 3/28/2023
 * Purpose: This program is intended to allow the user to edit a file and choose whether to insert,
 *          delete, append, or list certain text based on user input. After the user is done editing
 *          the file, then they will enter "E" to exit out of the program and the original text file
 *          is automatically changed that contains the resulting text after the changes were made by
 *          the user.
 ****************************************************************************************************/

import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class FileEdit {

    public static ArrayList<String> lines = new ArrayList<>();  // initializing ArrayList collection lines

    public static void main(String[] args) throws IOException { 
        System.out.print("EDIT: ");
        Scanner keyboard = new Scanner(System.in);     // user enters full text file name
        String fileName = keyboard.nextLine();
        BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
        String str = "";
        int count = 0;  // tracks number of lines

        while((str=br.readLine())!=null) {
            lines.add(count, str);  // adds text from file to ArrayList object
            ++count;
            System.out.println(count + "> "+ str);
        }

        String text = " ";  
        String command = " ";
        ++count;
        System.out.print(count + "> ");   
        command = keyboard.nextLine();

        String command_Array[];     // array used to hold command strings

        boolean change;    

        while(!command.equals("E")) { // program ends when E is entered
            change = true;
            command_Array = command.split("\\s"); 

            if(command_Array[0].equals("I")) // calls Insert method based on command array
            {
                while(true) {
                    if(command_Array.length == 1)   { // insert text at current line
                        System.out.print(count + "> ");
                        text = keyboard.nextLine();

                        insert(text, lines.size()); 
                    }

                    else if(command_Array.length == 2)  { // insert at line n
                        count = Integer.parseInt(command_Array[1]); // parse text to obtain number of index of lines ArrayList
                        System.out.print((command_Array[1]) + "> ");
                        text = keyboard.nextLine();
                        int num = Integer.parseInt(command_Array[1]) - 1; // index of line to insert text

                        insert(text, num);
                    }

                    ++count;
                    System.out.print(count + "> ");

                    command = keyboard.nextLine();
                    command_Array = command.split("\\s"); // splits text input by user as for command

                    // determines whether to exit out of while loop of I
                    if(command_Array[0].equals("I")) {
                        change = false;
                        break;
                    }
                    else if(command_Array[0].equals("L")) {
                        change = false;
                        break;
                    }
                    else if(command_Array[0].equals("D")) {
                        change = false;
                        break;
                    }
                    else if(command_Array[0].equals("E")) {
                        change = false;
                        break;
                    }
                    else if(command_Array[0].equals("A")) {
                        change = false;
                        break;
                    }
                    else    {
                        change = true;
                        text = command;
                    }
                } 
            }

            // calls List method
            else if(command_Array[0].equals("L")) 
            {
                if(command_Array.length == 1)    {          // list current line
                    List(lines.size() - 1, lines.size() - 1);
                }
                else if(command_Array.length == 2)   {      // list only at line n
                    List(Integer.parseInt(command_Array[1]) - 1, Integer.parseInt(command_Array[1]) - 1); 
                }
                else if(command_Array.length == 3)   {      // list starting from line n to line m 
                    List(Integer.parseInt(command_Array[1]) - 1, Integer.parseInt(command_Array[2]) - 1);
                }
            }

            // calls Delete method
            else if(command_Array[0].equals("D")) 
            {
                if(command_Array.length == 1)    {          // delete current line
                    delete(lines.size() - 1, lines.size() - 1);
                }
                else if(command_Array.length == 2)   {      // delete line n only
                    delete(Integer.parseInt(command_Array[1]) - 1, Integer.parseInt(command_Array[1]) - 1);
                }
                else if(command_Array.length == 3)   {      // delete starting from line n to line m
                    delete(Integer.parseInt(command_Array[1]) - 1, Integer.parseInt(command_Array[2]) - 1);
                }
                count = lines.size();
            }

            // calls Append method
            else if(command_Array[0].equals("A")) { // append text at the end of the ArrayList
                text = keyboard.nextLine();
                append(text);
            }

            if(change != false) {
                System.out.print(count+"> ");
                count++;
                command = keyboard.nextLine();
            }
        }

        // After entering E, a new file is created of ArrayList lines collection

        FileWriter writer = new FileWriter(fileName);
        for(String str_out : lines) 
            writer.write(str_out + System.lineSeparator());

        writer.close();         // closes FileWriter

        keyboard.close();       // closes Scanner
    }

    // Append method
    public static void append(String text) {        
        lines.add(text);
    }

    // List method
    public static void List(int from, int to)  {        
        for(int i = from; i <= to; i++)
            System.out.println((i + 1) + "> " + lines.get(i));
    }

    // Insert method
    public static void insert(String text, int count)    {      
        lines.add(count, text);
    }

    // Delete method
    public static void delete(int from, int to)    {        
        for(int i = from; i <= to; i++)
            lines.remove(i);
    }

}