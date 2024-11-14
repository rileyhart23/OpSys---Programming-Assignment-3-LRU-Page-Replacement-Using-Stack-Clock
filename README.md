# OpSys---Programming-Assignment-3-LRU-Page-Replacement-Using-Stack-Clock
Programming Assignment 3: LRU Page Replacement Using Stack &amp; Clock
CPSC 340 - Operating Systems Concepts and Design
Fall 2024
Programming Assignment 3: LRU Page Replacement Using Stack & Clock
Objective
The objective of this assignment is to deepen your understanding of virtual memory
management and page replacement policies by implementing the Least Recently Used
(LRU) algorithm using two approaches: Stack and Clock. You will simulate LRU page
replacement with a C or java program, using a stack and doubly linked list, and test your
implementation with provided page references.
Due Date
Before 11:59 p.m. in D2L Dropbox, Friday, November 22, 2024
Assignment Details
Write a C or java program that simulates the LRU page replacement algorithm using two
distinct methods, Stack and Clock with a doubly linked list. Your program will:
LRU using Stack:
• Using a stack to implement LRU which is a typical approach where the most recently
used page is kept at the top of the stack, and the least recently used page is at the
bottom.
• Test your implementation on the given page references for a memory frame size of
3.
• Display each page replacement step and the final results for the reference string.
LRU using Clock:
• Implement the Clock page replacement algorithm with a circular doubly linked list.
• Use a structure to store the “second chance” bit for each page in the list.
• Display each page replacement step and the final results for the reference string.
Page Reference Data:
Your program should read a file named Pages.txt, provided on D2L, containing page
reference strings.
• 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2
• 1, 0, 2, 2, 1, 7, 6, 7, 0, 1, 2, 0, 3, 0, 4, 5, 1, 5, 2, 4, 5, 6, 7, 6, 7, 2, 4, 2, 7, 3, 3, 2, 3
• 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1
• 1, 2, 3, 4, 2, 1, 5, 6, 2, 1, 2, 3, 7, 6, 3, 2, 1, 2, 3, 6
Process each page reference string and run both the Stack-based LRU and Clock-based LRU
algorithms, outputting results separately for each.
Program Requirements
Linked List Structure: For the Clock algorithm, make the linked list circular to rotate
through pages continuously.
Input Handling: Your program should read the list of pages from Pages.txt and handle
multiple reference strings sequentially.
Display Output: Show the status of the memory frame after each page reference, indicating
page replacements as they occur. Include a summary of the page faults for each algorithm at
the end of each reference string test.
Memory Management: Ensure proper memory management, freeing all dynamically
allocated memory after each test.
Program Menu: When the program runs, prompt the user with the following menu
options:
1. Load Page References from Pages.txt
2. Run LRU with Stack-based Implementation
3. Run LRU with Clock-based Implementation
4. Display Results Summary
5. Exit Program
Submission Requirements
Submit your source code files as a single tarball or zip file to the D2L Dropbox.
Print your source code, formatted neatly, and attach this assignment sheet on top. Bring this
to the next class after submission.
Include a comment block with your name at the top of your main program file.
Checklist
Check Description
The program is a result of my own efforts; I
shared code with no one and received no
help.
The program compiles correctly.
I have tested the program, and it runs
correctly with my test input.
I have stapled my source code printout to
this checklist and will hand it in at the next
class.
Grading Rubric
Earned Points Description
20 points Program printout is neat and readable;
code style promotes understanding.
20 points Program is syntactically correct (compiles
without errors).
40 points Program is semantically correct (executes
as specified).
40 points Runs correctly, produces accurate results
for both LRU Stack and LRU Clock methods.
Penalty points Lateness, failure to submit printout/source
file/completed checklist, not stapled.
100 Total points
