package console;

import java.util.*;

import structures.*;

public class console {

    public static void main(String[] args) {
	DictTree tree = new DictTree();
	Scanner console = new Scanner(System.in);

	int select = getInput(console);

	while (select != 5) {
	    if (select == 1) { // Load unordered tree
		tree.read("dictionary.txt");
		System.out.println("Tree loaded...");
	    } else if (select == 2) { // Load saved tree
		tree.loadSaved();
		System.out.println("Tree loaded...");
	    } else if (select == 3) { // Get a definition
		System.out.print("What word? ");
		String input = console.nextLine();
		System.out.println(tree.get(input));
	    } else if (select == 4) { // Save dictionary's current state
		tree.save();
		System.out.println("Tree saved... ");
	    }

	    select = getInput(console);
	}

	System.out.println("Good-bye!");
	console.close();
    }

    /**
     * This method displays the menu and gets the user's choice and validates the
     * input.
     * 
     * @param console Scanner object being used to interact with console
     * @return User's choice for conditional logic
     */
    public static int getInput(Scanner console) {
	boolean valid = false;
	int choice = 0;

	while (!valid) {
	    System.out.println("1. Load dictionary from unordered pairs");
	    System.out.println("2. Load dictionary from serialized tree");
	    System.out.println("3. Define");
	    System.out.println("4. Save Dictionary");
	    System.out.println("5. Exit");
	    String input = console.nextLine();

	    // Validate the input
	    try {
		choice = Integer.parseInt(input);
		if (choice <= 5 && choice >= 1) {
		    valid = true;
		} else {
		    System.out.println("Please enter a valid choice 1-5.");
		}
	    } catch (NumberFormatException e) {
		System.out.println("Please enter a valid choice 1-5.");
	    }
	}

	return choice;
    }
}
