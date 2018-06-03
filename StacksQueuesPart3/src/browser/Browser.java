package browser;

import java.io.*;
import java.util.Iterator;
import exceptions.*;
import structures.*;

/**
 * This class helps the Console class be easier to read
 * and understand. It uses Stacks and a TwoWayQueue to track 
 * user history. Upon closing, it stores the current session
 * history into the history.txt file to be loaded upon
 * starting the browser next time.
 * @author adriansmith
 * @version 1.0
 */
public class Browser {
    Stack<String> pageHist;
    TwoWayQueue<String> current;
    Stack<String> forward;

    /**
     * This Constructor loads all previously visited websites 
     * into pageHist and instantiates all data structures being used.
     */
    public Browser() {
	// Instantiates structures
	pageHist = new Stack<String>();
	current = new TwoWayQueue<String>();
	forward = new Stack<String>();
	String line = null;

	// Grabs history.txt file
	File file = new File("history.txt");
	FileReader instream = null;

	// Reads in the history.txt file
	try {
	    instream = new FileReader(file);
	    BufferedReader input = new BufferedReader(instream);
	    while ((line = input.readLine()) != null) {
		pageHist.push(line);
	    }
	    input.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * User visits a new page and it gets added to history.
     * It clears the forward queue because if they visit a new site
     * then there are no pages to go forward to. 
     * @param newPage The page being visited
     */
    public void visit(String newPage) {
	// if newPage has not been visited in this session, add it to history
	// else don't
	// newPage should be all lowercase, trimmed, and have format whatever.com
	newPage = newPage.trim().toLowerCase();

	// push it on to current
	current.enqueueFirst(newPage);

	// clear forward queue since new site is being visited
	forward.clear();
    }

    /**
     * Takes the user back to the last page that was visited. 
     * If there are no more previous pages, an error message 
     * is displayed and keeps user at the current page 
     * being visited.
     */
    public void back() {
	try {
	    if (current.size() == 1) {
		throw new StackUnderflowException();
	    } else {
		forward.push(current.dequeueFirst());
	    }
	} catch (StackUnderflowException e) {
	    System.out.println("Error, there are no more pages in history.");
	}
    }

    /**
     * Takes the user forward to the page they were just on if 
     * there was a back operation executed. If there are no pages to 
     * go forward to, an error message is displayed and the user
     * stays on their current page.
     */
    public void forward() {
	try {
	    current.enqueueFirst(forward.pop());
	} catch (StackUnderflowException e) {
	    System.out.println("Error, there are no more pages in history.");
	}
    }

    /**
     * Prints the complete history of pages the user has visited.
     * Prints page history first and then session history.
     */
    public void printHistory() {
	Iterator<String> printing = pageHist.iterator();
	Iterator<String> sess = current.iterator();

	String display = "\nHistory: ";

	while (printing.hasNext()) {
	    display += printing.next();
	    if (printing.hasNext() || sess.hasNext())
		display += " --> ";
	}

	while (sess.hasNext()) {
	    display += sess.next();
	    if (sess.hasNext())
		display += " --> ";
	}
	
	System.out.println(display + "\n");
    }

    /**
     * Prints the history of the current browsing session.
     */
    public void printSessionHistory() {
	Iterator<String> printing = current.iterator();

	String display = "\nSession History: ";

	while (printing.hasNext()) {
	    display += printing.next();
	    if (printing.hasNext())
		display += " --> ";
	}
	
	System.out.println(display + "\n");
    }

    /**
     * Completely clears history, page history and the 
     * current browsing session history.
     */
    public void clearHistory() {
	pageHist.clear();
	current.clear();
	forward.clear();
    }

    /**
     * Closes the browser and writes the current history
     * to the history.txt file.
     */
    public void close() {
	for (String page : current) {
	    pageHist.push(page);
	}

	// Initializes the PrintWriter responsible for writing to master_list.txt
	File history = new File("history.txt");
	PrintWriter write;
	try {
	    write = new PrintWriter(history);

	    for (String page : pageHist) {
		write.println(page);
	    }

	    write.close();
	} catch (FileNotFoundException e) {
	    write = null;
	    e.printStackTrace();
	}
    }
}
