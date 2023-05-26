/**************************************************************************************************************************************
 * COSC 3420.501
 * Project #5
 * Due Date: 5/04/2023
 * Created by Lizelle Castro and Nathan Egot
 * Purpose:     This program is intended to prompt the user to enter 2 files for the list of judges with their respective categories
 *              and the list of projects with their respective categories. The information from these files will be obtained and 
 *              place at most 3 judges with at most 6 projects for each category for the Science Fair. The program will then create a 
 *              new text file as projectgroups.txt that will have the new groups written.
 **************************************************************************************************************************************/

 import java.io.*;
 import java.util.*;
 
 class Project {
     int number;
     String category;
 
     public Project(int n, String c) {
         number = n;
         category = c;
     }
 }
 
 class Judge {
     String name;
     ArrayList<String> categories;
 
     public Judge(String n, ArrayList<String> c) {
         name = n;
         categories = c;
     }
 }
 
 class Group {
     String category;
     int group;
     String[] names;
     int[] projects;
     int count;
 
     public Group(String c, int g) {
         category = c;
         group = g;
         names = new String[3];
         projects = new int[6];
         count = 0;
     }
 
     public void setCategory(String c) {
         category = c;
     }
 
     public void printGroup(FileWriter wr) throws IOException {
         wr.write(category + "_" + group + ": ");
         for (int i = 0; i < 3; i++) {
             if (names[i] != null) {
                 wr.write(names[i] +", ");
             }
         }
         
         wr.write("-----> Projects: ");
         for (int i = 0; i < count; i++) {
             wr.write(projects[i] + " ");
         }
         wr.write(System.lineSeparator());
     }
 
 
 }
 
 public class ScienceFair {
     public static void main(String[] args) throws IOException {
         // Read input file with list of projects
         ArrayList<Project> projects = new ArrayList<>();
        
         System.out.print("Enter Projects file path: ");
             Scanner sc = new Scanner(System.in);
             String file = sc.nextLine();
             BufferedReader br_p = new BufferedReader(new FileReader(new File(file)));
 
             //BufferedReader br_p = new BufferedReader(new FileReader("C:\\Users\\egotn\\Downloads\\Projects.txt"));
             String line;
             while ((line = br_p.readLine()) != null) {
                 String[] parts = line.split("\\s+");  // splits a string by space
                 int number = Integer.parseInt(parts[0]);
                 String category = parts[1];
                 projects.add(new Project(number, category));
             }
             br_p.close();
 
         // Read input file with list of judges
         ArrayList<Judge> judges = new ArrayList<>();
        
         System.out.print("Enter Judges file path: ");
             Scanner scn = new Scanner(System.in);
             String file2 = scn.nextLine();
             BufferedReader br_j = new BufferedReader(new FileReader(new File(file2)));
 
         //BufferedReader br_j = new BufferedReader(new FileReader("C:\\Users\\egotn\\Downloads\\Judges.txt"));
         String line_j;
         while ((line_j = br_j.readLine()) != null) {
             String[] parts = line_j.split(":");
             String name = parts[0].trim();
             String[] categories = parts[0].trim().split(",");  
             ArrayList<String> categoryList = new ArrayList<>();
             for (String category : categories) {
                 categoryList.add(category.trim());
             }
             judges.add(new Judge(name, categoryList));
         }
         br_j.close();
        
         // Assign judges to projects
        
         Map<String, ArrayList<Project>> projectGroups = new HashMap<>();  // stores in key/value pairs
         for (Project project : projects) {
             ArrayList<Project> group = projectGroups.getOrDefault(project.category, new ArrayList<>());
             group.add(project);
             projectGroups.put(project.category, group);
             }
         ArrayList<Group> outputGroups = new ArrayList<>();
         for (HashMap.Entry<String, ArrayList<Project>> entry : projectGroups.entrySet()) {  // entrySet method used to return a set view of the mappings contained in the map
             String category = entry.getKey(); // key from entry
             ArrayList<Project> group = entry.getValue(); // value from entry
             int numGroups = (group.size() + 5) / 6; // Calculate number of groups needed
 
             Collections.shuffle(judges);
 
             // Assign judges to groups
             ArrayList<String> assignedJudges = new ArrayList<>();
             for (int i = 0; i < numGroups; i++) {
                 int startIndex = i * 3;
                 int endIndex = Math.min(startIndex + 3, judges.size()); // minimum between the two
                
                 ArrayList<String> judgesForGroup = new ArrayList<>();
                 for (int j = startIndex; j < endIndex; j++) {
                     Judge judge = judges.get(j);
                     // Check if judge is assigned to a category in the current group
                     boolean hasCategory = false;
                     for (String idx : judge.categories) {
                         if (category.equals(idx)) {      
                             hasCategory = true;
                             break;
                         }
                     }
                     // If judge is not assigned to a category in the current group, assign them
                     if (hasCategory == false && assignedJudges.contains(judge.name) == false) {
                         judgesForGroup.add(judge.name);
                         assignedJudges.add(judge.name);
                     }
                 }
 
                 // Create outGroup object for current category and group
                 Group outgroup = new Group(category, i + 1);
                 // Set assigned judges for current group
                 outgroup.names = judgesForGroup.toArray(new String[0]);
                 // Set projects for current group
                 for (int j = 0; j < 6 && j < group.size(); j++) {
                     outgroup.projects[j] = group.get(j).number;
                     outgroup.count++;
                 }
 
                 outputGroups.add(outgroup);
             }
         }
        
         FileWriter writer = new FileWriter("projectgroups.txt");
         // Write all information onto output file
         for (Group outGroup : outputGroups) {
             outGroup.printGroup(writer);
         }
        writer.close();
     }
 }