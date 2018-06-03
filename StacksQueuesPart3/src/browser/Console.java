/*
 * Adrian Smith
 * Turned in: February 5th, 2018
 * Class: IT 333 Data Structures and Algorithms
 * 
 * This program emulates an internet browser and how it would
 * handle a browsing session using a customly created Browser class
 * that uses Stack Data Structures and a Two Way Queue Data Structure. 
 */
package browser;

import java.util.*;

/**
 * This class controls the user input and makes calls to the browser methods
 * responsible for each user input choice.
 * 
 * @author adriansmith
 * @version 1.0
 */
public class Console {
    public static void main(String[] args) {
	Scanner console = new Scanner(System.in);
	Browser browser = new Browser();

	int select = displayMenu(console);

	while (select != 7) {
	    if (select == 1) { // Visit page
		System.out.print("Enter the page you wish to visit: ");
		String newPage = console.nextLine();
		browser.visit(newPage);
	    } else if (select == 2) { // Go back
		browser.back();
	    } else if (select == 3) { // Go forward
		browser.forward();
	    } else if (select == 4) { // Print history
		browser.printHistory();
	    } else if (select == 5) { // Print session history
		browser.printSessionHistory();
	    } else if (select == 6) { // Clear history
		browser.clearHistory();
	    }

	    select = displayMenu(console);
	}

	browser.close();
	console.close();
    }

    /**
     * This method simply displays the menu, gets the user choice, and 
     * validates the choice.
     * @param console Scanner object being used to get user input
     * @return user choice in the inclusive range (1-7)
     */
    public static int displayMenu(Scanner console) {
	boolean valid = false;
	int choice = 0;

	while (!valid) {
	    System.out.println("1. Visit Page");
	    System.out.println("2. Go Back");
	    System.out.println("3. Go Forward");
	    System.out.println("4. Print History");
	    System.out.println("5. Print Session History");
	    System.out.println("6. Clear History");
	    System.out.println("7. Close browser");
	    String input = console.nextLine();

	    try {
		choice = Integer.parseInt(input);
		if (choice <= 7 && choice >= 1) {
		    valid = true;
		} else {
		    System.out.println("Please enter a valid choice 1-7.");
		}
	    } catch (NumberFormatException e) {
		System.out.println("Please enter a valid choice 1-7.");
	    }
	}

	return choice;
    }
}
