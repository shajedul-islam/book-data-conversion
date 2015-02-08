package main.java.com.escenic.bdc.exception;

/**
 * Custom exception to check if the desired output format is supported/valid
 * @author Shajedul Islam
 *
 */
public class InvalidOutputFormatException extends Exception {

	public InvalidOutputFormatException() {
		super();
	}
	
	public InvalidOutputFormatException(String message) {
		super(message);
	}
}
