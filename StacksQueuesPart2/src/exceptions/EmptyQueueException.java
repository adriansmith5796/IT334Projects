/*
 * Adrian Smith
 * IT 333
 * Custom exception class for the TwoWayQueue class. 
 */
package exceptions;

/**
 * This class is meant to be used by the TwoWayQueue and is meant to be 
 * thrown when there's an attempt to remove an element from the queue 
 * but there's no elements to be removed.
 * @author adriansmith
 * @version 1.0
 */
public class EmptyQueueException extends RuntimeException{
    private String message;
    
    /**
     * Constructor that initializes the message with the default message of how this 
     * exception is meant to be used.
     */
    public EmptyQueueException() {
	this.message = "There are no elements in the queue to remove.";
    }
    
    /**
     * This constructor allows the user to put their own message in the exception.
     * @param message The message that the user is putting in the exception.
     */
    public EmptyQueueException(String message) {
	this.message = message;
    }
    
    /**
     * Gets the message stored in the exception.
     * 
     * @return the message variable
     */
    public String getMessage() {
	return this.message;
    }
}
