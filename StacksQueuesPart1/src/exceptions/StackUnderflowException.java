package exceptions;

/**
 * This is a custom Exception class for the Stack data structure.
 * It gets thrown if/when the client tries to pop off an element 
 * but the Stack has no elements to pop off.
 * @author adriansmith
 * @version 1.0
 */
public class StackUnderflowException extends IllegalArgumentException {
    String message;
    public StackUnderflowException() {
	this.message = "Cannot pop off an element while Stack has no elements to pop.";
    }
}
