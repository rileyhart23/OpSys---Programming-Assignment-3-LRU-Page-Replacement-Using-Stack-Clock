// Operating Systems Concepts and Design CPSC 340:
// Programming Assignment 3: LRU Page Replacement Using Stack & Clock
// Due Date: 11/22/2024
// Riley Wasdyke



import java.util.Scanner;
import java.io.*;


public class Main {

    public static void main(String[] args) {
        ProcessManager manager = new ProcessManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {

            System.out.println("\nProgram Menu:");
            System.out.println("1. Load Page References from Pages.txt");
            System.out.println("2. Run LRU with Stack-based Implementation");
            System.out.println("3. Run LRU with Clock-based Implementation");
            System.out.println("4. Display Results Summary");
            System.out.println("5. Exit Program");


            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                    case 1:
                        manager.loadPageReferences();
                        break;
                    case 2:
                        manager.runLRUStack();
                        break;  
                    case 3:
                        manager.runLRUClock();
                        break;
                    case 4:
                        manager.displayResultsSummary();
                        break;
                    case 5: 
                        manager.exitProgram();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void loadPageReferences() {
        
    }

    public void runLRUStack() {

    }

    public void runLRUClock() {

    }

    public void displayResultsSummary() {

    }

    public void exitProgram() {

    }
}



