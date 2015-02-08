package main.java.com.escenic.bdc.exception;

/**
 * Custom exception to check if the desired output format is supported/valid
 * @author Shajedul Islam
 *
 */
public class InvalidBookXmlDataException extends Exception {

	public InvalidBookXmlDataException() {
		super();
	}
	
	public InvalidBookXmlDataException(String message) {
		super(message);
	}
}
