// CPSC 340 Operating Systems Concepts and Design:
//# OpSys---Programming-Assignment-3-LRU-Page-Replacement-Using-Stack-Clock
// Riley Wasdyke
// 11/22/2024

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    private static List<List<Integer>> pageReferences = new ArrayList<>();
    private static List<Integer> stackLRUResults = new ArrayList<>();
    private static List<Integer> clockLRUResults = new ArrayList<>();

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

    public static void loadPageReferences() {
        String fileName = "Pages.txt";

        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            pageReferences.clear();
            stackLRUResults.clear();
            clockLRUResults.clear();

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] numbers = line.split(",");
                    List<Integer> pageList = new ArrayList<>();
                    for (String num : numbers) {
                        num = num.trim();
                        try {
                            pageList.add(Integer.parseInt(num));
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number format in file: " + num);
                        }
                    }
                    pageReferences.add(pageList);
                }
            }

            System.out.println("\nPage references loaded from Pages.txt:");
            for (List<Integer> pageList : pageReferences) {
                System.out.println(pageList);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
    }

    public static void runLRUStack() {
        if (pageReferences.isEmpty()) {
            System.out.println("Please load page references first.");
            return;
        }

        int frameSize = 3;
        List<Integer> pageFrame = new ArrayList<>();
        stackLRUResults.clear();

        for (List<Integer> pageList : pageReferences) {
            System.out.println("Processing page reference string: " + pageList);
            int pageFaults = 0;
            pageFrame.clear();

            for (int page : pageList) {
                if (pageFrame.contains(page)) {
                    pageFrame.remove(Integer.valueOf(page));
                    pageFrame.add(0, page);
                } else {
                    if (pageFrame.size() == frameSize) {
                        pageFrame.remove(pageFrame.size() - 1);
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

    static class Page {
        int pageNumber;
        boolean secondChance;

        public Page(int pageNumber) {
            this.pageNumber = pageNumber;
            this.secondChance = false;
        }
    }

    public static void runLRUClock() {
        if (pageReferences.isEmpty()) {
            System.out.println("Please load page references first.");
            return;
        }

        int frameSize = 3;
        LinkedList<Page> pageFrame = new LinkedList<>();
        clockLRUResults.clear();

        for (List<Integer> pageList : pageReferences) {
            System.out.println("Processing page reference string: " + pageList);
            int pageFaults = 0;
            pageFrame.clear();

            for (int page : pageList) {
                boolean pageFound = false;

                for (Page p : pageFrame) {
                    if (p.pageNumber == page) {
                        p.secondChance = true;
                        pageFound = true;
                        break;
                    }
                }

                if (!pageFound) {
                    if (pageFrame.size() < frameSize) {
                        pageFrame.addLast(new Page(page));
                        pageFaults++;
                    } else {
                        while (true) {
                            Page oldestPage = pageFrame.getFirst();
                            if (oldestPage.secondChance) {
                                oldestPage.secondChance = false;
                                pageFrame.addLast(pageFrame.removeFirst());
                            } else {
                                pageFrame.removeFirst();
                                pageFrame.addLast(new Page(page));
                                pageFaults++;
                                break;
                            }
                        }
                    }
                }

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

    public static void displayResultsSummary() {
        if (pageReferences.isEmpty()) {
            System.out.println("Please load page references first.");
            return;
        }

        System.out.println("\nResults Summary:");

        System.out.println("\nLRU Stack Results:");
        for (int i = 0; i < stackLRUResults.size(); i++) {
            System.out.println("Reference string " + pageReferences.get(i) +
                    " -> Page Faults: " + stackLRUResults.get(i));
        }

        System.out.println("\nLRU Clock Results:");
        for (int i = 0; i < clockLRUResults.size(); i++) {
            System.out.println("Reference string " + pageReferences.get(i) +
                    " -> Page Faults: " + clockLRUResults.get(i));
        }
    }

    public static void exitProgram() {
        System.out.println("Exiting program...");
        System.exit(0);
    }
}
