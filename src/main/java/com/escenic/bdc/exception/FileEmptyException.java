package main.java.com.escenic.bdc.exception;

/**
 * Custom exception for checking if the input file is empty or not
 * @author Shajedul Islam
 *
 */
public class FileEmptyException extends Exception {

	public FileEmptyException() {
		super();
	}
	
	public FileEmptyException(String message) {
		super(message);
	}
}
