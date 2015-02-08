package main.java.com.escenic.bdc.exception;

/**
 * Custom exception to check if the desired output format is supported/valid
 * @author Shajedul Islam
 *
 */
public class InvalidBookTextDataException extends Exception {

	public InvalidBookTextDataException() {
		super();
	}
	
	public InvalidBookTextDataException(String message) {
		super(message);
	}
}
