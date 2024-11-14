import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<List<Integer>> pageReferences = new ArrayList<>();

    public static void main(String[] args) {
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

            switch (choice) {
                case 1:
                    loadPageReferences();
                    break;
                case 2:
                    runLRUStack();
                    break;
                case 3:
                    runLRUClock();
                    break;
                case 4:
                    displayResultsSummary();
                    break;
                case 5:
                    exitProgram();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    // Load page references from Pages.txt
    public static void loadPageReferences() {
        String fileName = "Pages.txt"; // Ensure the file is in the correct directory

        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            pageReferences.clear(); // Clear any existing page references

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                
                if (!line.isEmpty()) {
                    // Split the line by commas and process each number
                    String[] numbers = line.split(",");
                    List<Integer> pageList = new ArrayList<>();
                    for (String num : numbers) {
                        num = num.trim(); // Remove any extra spaces around numbers
                        try {
                            int pageNumber = Integer.parseInt(num);
                            pageList.add(pageNumber);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number format in file: " + num);
                        }
                    }
                    pageReferences.add(pageList); // Add the list of page references from the line
                }
            }

            // Display the loaded page references in the same format as Pages.txt
            System.out.println("\nPage references loaded from Pages.txt:");

            // Print the page references in the same format (one line for each list of page numbers)
            for (List<Integer> pageList : pageReferences) {
                for (int i = 0; i < pageList.size(); i++) {
                    if (i > 0) {
                        System.out.print(", ");
                    }
                    System.out.print(pageList.get(i));
                }
                System.out.println(); // Move to the next line after printing each list
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
    }

    // Other methods for LRU stack, LRU clock, display, exit, etc. will go here.
    public static void runLRUStack() {
        // Your implementation for LRU using Stack goes here
    }

    public static void runLRUClock() {
        // Your implementation for LRU using Clock goes here
    }

    public static void displayResultsSummary() {
        // Your implementation for displaying results goes here
    }

    public static void exitProgram() {
        System.out.println("Exiting program...");
        System.exit(0);
    }
}
