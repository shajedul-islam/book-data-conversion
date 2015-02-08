package test.java.com.escenic.bdc.reader;

import static org.junit.Assert.*;

import java.util.Arrays;

import main.java.com.escenic.bdc.exception.InvalidBookTextDataException;
import main.java.com.escenic.bdc.jaxb.beans.Authors;
import main.java.com.escenic.bdc.jaxb.beans.Book;
import main.java.com.escenic.bdc.reader.BookDataReader;
import main.java.com.escenic.bdc.reader.BookDataReaderFactory;
import main.java.com.escenic.bdc.util.ApplicationConstants;

import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BookTextDataReaderTest {
	
	/**
	 * Common method that gets a reader object and invokes read method
	 * This method is called from every test methods that expects an error message
	 * @param bookData
	 * @param expectedErrorMessage
	 * @throws Exception
	 */
	private void executeReadExpectingErrorMessage(String bookData, String expectedErrorMessage) throws Exception {
		BookDataReaderFactory readerFactory = new BookDataReaderFactory();
		BookDataReader reader = readerFactory.getBookDataReader(ApplicationConstants.BOOK_DATA_FORMAT_TEXT);
		try {
			reader.read(bookData);
		} catch (InvalidBookTextDataException ex) {
			assertTrue(expectedErrorMessage.equals(ex.getMessage()));
		}
	}
	
	@Test
	public void testReadWithNoColonSeparatedData() throws Exception {

		String bookTextDataWithNoColonSeparatedData = "name Effective Java\nauthors Joshua Bloch, James gosling\nisbn: u9348984995898493\npublished date: October 2005";
		String expectedErrorMessage = "Error: Book text data not valid. Please provide \":\" separated key value pair!";
		
		executeReadExpectingErrorMessage(bookTextDataWithNoColonSeparatedData, expectedErrorMessage);;
	}
	
	@Test
	public void testReadWithNoBookNameInData() throws Exception {

		String bookTextDataWithNoName = "name: \nauthors: Joshua Bloch, James gosling\nisbn: u9348984995898493\npublished date: October 2005";
		String expectedErrorMessage = "Error: Book text data not valid. Please provide \":\" separated key value pair!";
		
		executeReadExpectingErrorMessage(bookTextDataWithNoName, expectedErrorMessage);;
	}
	
	@Test
	public void testReadWithNoAuthorInData() throws Exception {

		String bookTextDataWithNoAuthor = "name: Effective Java \nauthors: \nisbn: u9348984995898493\npublished date: October 2005";
		String expectedErrorMessage = "Error: Book text data not valid. Please provide \":\" separated key value pair!";
		
		executeReadExpectingErrorMessage(bookTextDataWithNoAuthor, expectedErrorMessage);;
	}
	
	@Test
	public void testReadWithNoIsbnInData() throws Exception {

		String bookTextDataWithNoIsbn = "name: Effective Java\nauthors: Joshua Bloch, James gosling\nisbn: \npublished date: October 2005";
		String expectedErrorMessage = "Error: Book text data not valid. Please provide \":\" separated key value pair!";
		
		executeReadExpectingErrorMessage(bookTextDataWithNoIsbn, expectedErrorMessage);;
	}
	
	@Test
	public void testReadWithNoPublishedDateInData() throws Exception {

		String bookTextDataWithNoPublishedDate = "name: Effective Java\nauthors: Joshua Bloch, James gosling\nisbn: u9348984995898493\npublished date:";
		String expectedErrorMessage = "Error: Book text data not valid. Please provide \":\" separated key value pair!";
		
		executeReadExpectingErrorMessage(bookTextDataWithNoPublishedDate, expectedErrorMessage);;
	}
	
	@Test
	public void testReadWithMissingKeyName() throws Exception {

		String bookTextDataWithMissingKeyName = "\nauthors: Joshua Bloch, James gosling\nisbn: u9348984995898493\npublished date: October 2005";
		String expectedErrorMessage = "Error: Book text data not valid. Following input keys missing: name"; 
		
		executeReadExpectingErrorMessage(bookTextDataWithMissingKeyName, expectedErrorMessage);;
	}
	
	@Test
	public void testReadWithMissingKeyAuthors() throws Exception {

		String bookTextDataWithMissingKeyAuthors = "name: Effective Java\n \nisbn: u9348984995898493\npublished date: October 2005";
		String expectedErrorMessage = "Error: Book text data not valid. Following input keys missing: authors";
		
		executeReadExpectingErrorMessage(bookTextDataWithMissingKeyAuthors, expectedErrorMessage);;
	}
	
	@Test
	public void testReadWithMissingKeyIsbn() throws Exception {

		String bookTextDataWithMissingKeyIsbn = "name: Effective Java\nauthors: Joshua Bloch, James gosling\npublished date: October 2005";
		String expectedErrorMessage = "Error: Book text data not valid. Following input keys missing: isbn";
		
		executeReadExpectingErrorMessage(bookTextDataWithMissingKeyIsbn, expectedErrorMessage);;
	}
	
	@Test
	public void testReadWithMissingKeyPublishedDate() throws Exception {

		String bookTextDataWithMissingKeyPublishedDate = "name: Effective Java\nauthors: Joshua Bloch, James gosling\nisbn: u9348984995898493\n";
		String expectedErrorMessage = "Error: Book text data not valid. Following input keys missing: published date";
		
		executeReadExpectingErrorMessage(bookTextDataWithMissingKeyPublishedDate, expectedErrorMessage);;
	}
	
	@Test
	public void testReadWithValidTextData() throws Exception {

		String validBookTextData = "name: Effective Java\nauthors: Joshua Bloch, James gosling\nisbn: u9348984995898493\npublished date: October 2005";
		
		Book mockBook = new Book();
		mockBook.setName("Effective Java");
		Authors authors = new Authors();
		authors.setAuthor(Arrays.asList("Joshua Bloch", "James gosling"));
		mockBook.setAuthors(authors);
		mockBook.setPublishedDate("October 2005");
		
		BookDataReaderFactory readerFactory = new BookDataReaderFactory();
		BookDataReader reader = readerFactory.getBookDataReader(ApplicationConstants.BOOK_DATA_FORMAT_TEXT);
		Book book = reader.read(validBookTextData);
		
		assertNotNull(book);
		assertTrue(mockBook.getName().equals(book.getName()));
		
		assertTrue(mockBook.getAuthors().getAuthor().containsAll(book.getAuthors().getAuthor()) && 
				book.getAuthors().getAuthor().containsAll(mockBook.getAuthors().getAuthor()));
		
		assertTrue(mockBook.getPublishedDate().equals(book.getPublishedDate()));
		
	}

}
