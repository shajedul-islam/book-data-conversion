package main.java.com.escenic.bdc.reader;

import javax.xml.bind.UnmarshalException;

import main.java.com.escenic.bdc.jaxb.beans.Book;

/**
 * Applying Facade pattern to encapsulate the implementation of book reader
 * 
 * An interface for reading book data from a file 
 * that can be of multiple format i.e. text/xml/json 
 * @author Shajedul Islam
 *
 */
public interface BookDataReader {

	/**
	 * Reads book data from file
	 * @param bookData 	book data in string
	 * @return	the book object that is created out of the string data
	 */
	Book read(String bookData) throws Exception;
	
}
