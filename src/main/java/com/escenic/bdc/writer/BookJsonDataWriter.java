package main.java.com.escenic.bdc.writer;

import main.java.com.escenic.bdc.jaxb.beans.Book;

import com.google.gson.Gson;

/**
 * Implementation of BookDataWriter
 * Writes book data in Json format
 * @author Shajedul Islam
 *
 */
public class BookJsonDataWriter implements BookDataWriter {
	
	/**
	 * Writes book data in Json format
	 * @param book the book object parsed from the file
	 */
	@Override
	public String write(Book book) {

		Gson gson = new Gson();
		String bookJsonString = gson.toJson(book);
		
		return bookJsonString.toString();
	}

	
}
