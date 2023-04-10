/*

 * Class: 44-241 Computer Programming II

 * Author: Kimberly Mays

 * Description: Project 1

  * Due: April 9, 2023

  * I pledge that I have completed the programming assignment independently.

  * I have not copied the code from a student or any source.

  * I have not given my code to any other student and will not share this code with anyone under any circumstances.

 */
package project01;

import java.io.*;  //this lets you interact with files
import java.util.*;  //this lets you use Scanner
import javax.swing.*;  //this is to use JFileChooser
import java.util.ArrayList;  //import this to use Array List which is used for a method
        
public class Project01 {
   
    public static void main(String[] args) throws FileNotFoundException {
        
        JFileChooser myChooser = new JFileChooser(); //allows user to choose file they want
        
        myChooser.showOpenDialog (null);
        File file = myChooser.getSelectedFile();
        Scanner fileInput = new Scanner(file);
        
        String[][]dataSet = find(fileInput);  
        
        Scanner input = new Scanner (System.in);
        String choice;
        
        do {
            System.out.println("What would you like to do.");
            System.out.println("Menu (Choose one of the following or Q to quit): ");
            System.out.println("F -Filter on Type");
            System.out.println("D -Filter on Date");
            System.out.println("S -Search by Show");
            System.out.println("H -Highest Days in Top Ten");
            System.out.print("Choice: ");
            choice = input.nextLine().toUpperCase(); //so user can enter upper or lower case letter choice
            
            switch (choice) { //all the choices the user has and each task executes based on user choice
                case "F":
                System.out.print("Would you like to filter on TV Show, Movie, or Stand-Up Comedy: ");
                String type = input.nextLine(); 
                filterOnType(dataSet, type); 
                break;
                
                case "D":
                System.out.print("What week would you like to search for (mm/dd/yyyy): ");
                String date = input.nextLine();
                filterOnDate(dataSet, date);  
                break;
                
                case "S":
                System.out.print("What TV Show or Movie would you like to search for: ");
                String show = input.nextLine();
                searchForShow(dataSet, show); 
                break;
                
                case "H":
                highestDaysInTopTen(dataSet);
                break;
                
                case "Q":
                    return; //Quits the program if the user's choice is q
                
                default:
                    System.out.print("Invalid Input");
                    break;
            }             
    }   while (choice != "Q"); 
        fileInput.close();
    }
    public static String[][] find(Scanner fileInput)throws FileNotFoundException  {  
        ArrayList<String[]> data = new ArrayList<>(); //Array List that reads and separates the file columns at the comma delimiter
        while (fileInput.hasNextLine()) {
            String line = fileInput.nextLine();
            String[]values = line.split(",");
            data.add(values);
        }
        return data.toArray(new String[data.size()][]);
    }
    
    public static void searchForShow(String[][]dataSet, String show) throws FileNotFoundException {
        int weeksAppeared = 0;
        boolean showFound = false; 
        PrintWriter fileWriter = new PrintWriter("searchResults.txt");
        fileWriter.println("Results for: " + show); //Method that searches for the given string in the 2D array;

        for (int i = 0; i < dataSet.length; i++) {
            
            if (dataSet[i][2].contains(show)) {
                showFound = true;
                weeksAppeared++; //to count the weeks the show or movie appeared
                
                for (int col = 0; col<6; col++) {
                    fileWriter.print(dataSet[i][0] +"\t" + dataSet[i][1] +"\t" + dataSet[i][2] +"\t" + dataSet[i][3] +"\t" + dataSet[i][4] +"\t" + dataSet[i][5] +"\n");
                }  //prints each row with a tab between the columns 
                System.out.println("The number of weeks " + show + " appeared is: " + weeksAppeared);    
                fileWriter.close (); 
            }
        }
        if (!showFound) {
                System.out.println("Show not found"); //message if show not found from user input
        }                        
    }
    public static void filterOnType (String[][] dataSet, String type) throws FileNotFoundException {
        PrintWriter fileWriter = new PrintWriter("type.txt");
        fileWriter.println("Results for: " + type); //Method that writes to a file called "type.txt"
        
        for (int i = 0; i < dataSet.length; i++) {
            if (dataSet[i][3].equalsIgnoreCase(type)) {
            
            fileWriter.print(dataSet[i][0] +"\t" + dataSet[i][1] +"\t" + dataSet[i][2] +"\t\t" +dataSet[i][3] +"\t\t" + dataSet[i][4] +"\t" + dataSet[i][5] + "\n");
            }//prints each row with a tab between the columns 
        }    
        fileWriter.close();
    }

    public static void filterOnDate (String[][] dataSet, String date) throws FileNotFoundException {
        PrintWriter fileWriter = new PrintWriter("date.txt");
        fileWriter.println("Results for: " + date); //Method that writes to a file called "date.txt"
        
        for (int i = 0; i < dataSet.length; i++) {
            if (dataSet[i][0].equals(date)) {
            
            fileWriter.print(dataSet[i][0] + "\t" + dataSet[i][1] +"\t" + dataSet[i][2] +"\t" + dataSet[i][3] +"\t" + dataSet[i][4] +"\t" + dataSet[i][5] + "\n");
            } //prints each row with a tab between the columns 
        }    
        fileWriter.close();
    }
    //Method displays the show/movie with the highest number of consecutive days in the top ten
    public static void highestDaysInTopTen (String[][] dataSet) {  
        int maxDays = 0;
        String maxShow = "";
        
        for (int i=0; i<dataSet.length; i++) {
            int days = Integer.parseInt(dataSet[i][5]);
            
            if (days > maxDays) {
                maxDays = days;
                maxShow = dataSet[1][2];
            }
        }    
            System.out.print("The show/movie with the highest number of consecutive days in the top ten is: " + maxShow + " with" + maxDays + " days!");
            }
        }
    

    
