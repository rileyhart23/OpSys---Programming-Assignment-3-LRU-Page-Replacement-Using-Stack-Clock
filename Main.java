// CPSC 340 Operating Systems Concepts and Design:
// Riley Wasdyke
// 11/22/2024
// Programming Assignment 3: LRU Page Replacement Using Stack and Clock Algorithms

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    // Stores page reference strings loaded from the file
    private static List<List<Integer>> pageReferences = new ArrayList<>();

    // Stores page faults results for the stack-based LRU implementation
    private static List<Integer> stackLRUResults = new ArrayList<>();

    // Stores page faults results for the clock-based LRU implementation
    private static List<Integer> clockLRUResults = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Main program loop to display menu and process user input
        do {
            System.out.println("\nProgram Menu:");
            System.out.println("1. Load Page References from Pages.txt");
            System.out.println("2. Run LRU with Stack-based Implementation");
            System.out.println("3. Run LRU with Clock-based Implementation");
            System.out.println("4. Display Results Summary");
            System.out.println("5. Exit Program");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

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

        scanner.close(); // Close scanner to free resources
    }

    // Method to load page reference strings from a file
    public static void loadPageReferences() {
        String fileName = "Pages.txt";

        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            // Clear any previous data
            pageReferences.clear();
            stackLRUResults.clear();
            clockLRUResults.clear();

            // Read and parse each line in the file
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] numbers = line.split(",");
                    List<Integer> pageList = new ArrayList<>();
                    for (String num : numbers) {
                        num = num.trim();
                        try {
                            pageList.add(Integer.parseInt(num)); // Parse valid numbers
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number format in file: " + num);
                        }
                    }
                    pageReferences.add(pageList);
                }
            }

            // Print loaded page references
            System.out.println("\nPage references loaded from Pages.txt:");
            for (List<Integer> pageList : pageReferences) {
                System.out.println(pageList);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
    }

    // Method to simulate LRU page replacement using a stack-based approach
    public static void runLRUStack() {
        if (pageReferences.isEmpty()) {
            System.out.println("Please load page references first.");
            return;
        }

        int frameSize = 3; // Set memory frame size
        List<Integer> pageFrame = new ArrayList<>(); // Represents the memory frame
        stackLRUResults.clear();

        // Process each page reference string
        for (List<Integer> pageList : pageReferences) {
            System.out.println("Processing page reference string: " + pageList);
            int pageFaults = 0;
            pageFrame.clear();

            for (int page : pageList) {
                if (pageFrame.contains(page)) {
                    // If the page is already in memory, move it to the front (most recently used)
                    pageFrame.remove(Integer.valueOf(page));
                    pageFrame.add(0, page);
                } else {
                    // If the page is not in memory, add it, evicting the least recently used if necessary
                    if (pageFrame.size() == frameSize) {
                        pageFrame.remove(pageFrame.size() - 1); // Remove LRU page
                    }
                    pageFrame.add(0, page);
                    pageFaults++;
                }
                System.out.println("Memory frame: " + pageFrame);
            }
            System.out.println("Total page faults for this reference string: " + pageFaults);
            stackLRUResults.add(pageFaults);
        }
    }

    // Class to represent a page for the clock algorithm
    static class Page {
        int pageNumber;
        boolean secondChance; // Indicates whether the page has a second chance

        public Page(int pageNumber) {
            this.pageNumber = pageNumber;
            this.secondChance = false;
        }
    }

    // Method to simulate LRU page replacement using a clock-based approach
    public static void runLRUClock() {
        if (pageReferences.isEmpty()) {
            System.out.println("Please load page references first.");
            return;
        }

        int frameSize = 3; // Set memory frame size
        LinkedList<Page> pageFrame = new LinkedList<>(); // Represents the memory frame
        clockLRUResults.clear();

        // Process each page reference string
        for (List<Integer> pageList : pageReferences) {
            System.out.println("Processing page reference string: " + pageList);
            int pageFaults = 0;
            pageFrame.clear();

            for (int page : pageList) {
                boolean pageFound = false;

                // Check if the page is already in memory
                for (Page p : pageFrame) {
                    if (p.pageNumber == page) {
                        p.secondChance = true; // Give the page a second chance
                        pageFound = true;
                        break;
                    }
                }

                if (!pageFound) {
                    // If the page is not in memory, handle replacement
                    if (pageFrame.size() < frameSize) {
                        pageFrame.addLast(new Page(page));
                        pageFaults++;
                    } else {
                        while (true) {
                            Page oldestPage = pageFrame.getFirst();
                            if (oldestPage.secondChance) {
                                // Remove second chance and move to the back of the queue
                                oldestPage.secondChance = false;
                                pageFrame.addLast(pageFrame.removeFirst());
                            } else {
                                // Replace the page without a second chance
                                pageFrame.removeFirst();
                                pageFrame.addLast(new Page(page));
                                pageFaults++;
                                break;
                            }
                        }
                    }
                }

                // Display the current state of the memory frame
                System.out.print("Memory frame: ");
                for (Page p : pageFrame) {
                    System.out.print(p.pageNumber + " ");
                }
                System.out.println();
            }
            System.out.println("Total page faults for this reference string: " + pageFaults);
            clockLRUResults.add(pageFaults);
        }
    }

    // Method to display a summary of results for both algorithms
    public static void displayResultsSummary() {
        if (pageReferences.isEmpty()) {
            System.out.println("Please load page references first.");
            return;
        }

        System.out.println("\nResults Summary:");

        // Display stack-based LRU results
        System.out.println("\nLRU Stack Results:");
        for (int i = 0; i < stackLRUResults.size(); i++) {
            System.out.println("Reference string " + pageReferences.get(i) +
                    " -> Page Faults: " + stackLRUResults.get(i));
        }

        // Display clock-based LRU results
        System.out.println("\nLRU Clock Results:");
        for (int i = 0; i < clockLRUResults.size(); i++) {
            System.out.println("Reference string " + pageReferences.get(i) +
                    " -> Page Faults: " + clockLRUResults.get(i));
        }
    }

    // Method to exit the program
    public static void exitProgram() {
        System.out.println("Exiting program...");
        System.exit(0);
    }
}
