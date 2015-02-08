package main.java.com.escenic.bdc.writer;

import main.java.com.escenic.bdc.jaxb.beans.Book;

/**
 * Applying Facade pattern to encapsulate the implementation of book writer
 * 
 * An interface for writing book data in desired format i.e. text/xml/json 
 * @author Shajedul Islam
 *
 */
public interface BookDataWriter {

	/**
	 * writes book data in desired output format i.e. text/xml/json
	 * @param book The book object parsed from the file
	 * @return book data string in text/xml format
	 */
	String write(Book book);
	
}
