package main.java.com.escenic.bdc.writer;

import java.util.List;

import main.java.com.escenic.bdc.jaxb.beans.Book;

/**
 * Implementation of BookDataWriter
 * Writes book data in Text format
 * @author Shajedul Islam
 *
 */
public class BookTextDataWriter implements BookDataWriter {
	
	/**
	 * Writes book data in Text format
	 * @param book the book object parsed from the file
	 */
	@Override
	public String write(Book book) {

		String newLine = System.getProperty("line.separator");
		StringBuilder bookTextString = new StringBuilder();
		
		bookTextString.append("Name: " + book.getName());
		bookTextString.append(newLine);
		
		String delimeter = "";
		StringBuilder authorString = new StringBuilder();
		List<String> authors = book.getAuthors().getAuthor();
		for (String author: authors) {
			authorString.append(delimeter).append(author);
        	delimeter = ", ";
        }
		
		bookTextString.append("Authors: " + authorString);
		bookTextString.append(newLine);
		bookTextString.append("ISBN: " + book.getIsbn());
		bookTextString.append(newLine);
		bookTextString.append("Published Date: " + book.getPublishedDate());
		
		return bookTextString.toString();
	}

	
}
