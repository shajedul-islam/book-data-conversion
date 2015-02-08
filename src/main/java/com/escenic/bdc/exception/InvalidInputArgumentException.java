package main.java.com.escenic.bdc.exception;

/**
 * Custom exception to check if the input arguments are valid or not
 * @author Shajedul Islam
 *
 */
public class InvalidInputArgumentException extends Exception {

	public InvalidInputArgumentException() {
		super();
	}
	
	public InvalidInputArgumentException(String message) {
		super(message);
	}
}
