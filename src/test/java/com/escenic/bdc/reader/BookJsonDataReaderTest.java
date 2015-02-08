package test.java.com.escenic.bdc.reader;

import static org.junit.Assert.assertTrue;
import main.java.com.escenic.bdc.reader.BookDataReader;
import main.java.com.escenic.bdc.reader.BookDataReaderFactory;
import main.java.com.escenic.bdc.util.ApplicationConstants;

import org.junit.Test;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

public class BookJsonDataReaderTest {
	
	/**
	 * Common method that gets a reader object and invokes read method
	 * This method is called from every test methods that expects an error message
	 * @param bookData
	 * @param expectedErrorMessage
	 * @throws Exception
	 */
	private void executeReadExpectingErrorMessage(String bookData, String expectedErrorMessage) throws Exception {
		BookDataReaderFactory readerFactory = new BookDataReaderFactory();
		BookDataReader reader = readerFactory.getBookDataReader(ApplicationConstants.BOOK_DATA_FORMAT_JSON);
		try {
			reader.read(bookData);
		} catch (JsonParseException ex) {
			assertTrue(expectedErrorMessage.equals(ex.getMessage()));
		}
	}
	
	@Test
	public void testReadWithNoElementBook() throws Exception {
		
		String bookJsonDataWithNoElementBook = "{"
				
				+ "\nname: \"Effective Java\","
				+ "\nauthors: [\"Joshua Bloch\", \"James Gosling\"],"
				+ "\nisbn: \"u9348984995898493\","
				+ "\npublishedDate: \"October 2005\""
				+ "\n}";

		String expectedErrorMessage = "Error: Required Field Not Found: book!";
		
		executeReadExpectingErrorMessage(bookJsonDataWithNoElementBook, expectedErrorMessage);;
	}
	
	@Test
	public void testReadWithNoElementName() throws Exception {
		
		String bookJsonDataWithNoElementName = "{"
				+ "\nbook: {"
				
				+ "\nauthors: [\"Joshua Bloch\", \"James Gosling\"],"
				+ "\nisbn: \"u9348984995898493\","
				+ "\npublishedDate: \"October 2005\""
				+ "\n}"
				+ "\n}";

		String expectedErrorMessage = "Error: Required Field Not Found: name!";
		
		executeReadExpectingErrorMessage(bookJsonDataWithNoElementName, expectedErrorMessage);;
	}
	
	@Test
	public void testReadWithNoElementISBN() throws Exception {
		
		String bookJsonDataWithNoElementISBN = "{"
				+ "\nbook: {"
				+ "\nname: \"Effective Java\","
				+ "\nauthors: [\"Joshua Bloch\", \"James Gosling\"],"
				
				+ "\npublishedDate: \"October 2005\""
				+ "\n}"
				+ "\n}";

		String expectedErrorMessage = "Error: Required Field Not Found: isbn!";
		
		executeReadExpectingErrorMessage(bookJsonDataWithNoElementISBN, expectedErrorMessage);;
	}
	
	@Test
	public void testReadWithNoElementPublishedDate() throws Exception {
		
		String bookJsonDataWithNoElementPublishedDate = "{"
				+ "\nbook: {"
				+ "\nname: \"Effective Java\","
				+ "\nauthors: [\"Joshua Bloch\", \"James Gosling\"],"
				+ "\nisbn: \"u9348984995898493\""
				
				+ "\n}"
				+ "\n}";

		String expectedErrorMessage = "Error: Required Field Not Found: publishedDate!";
		
		executeReadExpectingErrorMessage(bookJsonDataWithNoElementPublishedDate, expectedErrorMessage);;
	}
	
	@Test
	public void testReadWithNoElementAuthors() throws Exception {
		
		String bookJsonDataWithNoElementAuthors = "{"
				+ "\nbook: {"
				+ "\nname: \"Effective Java\","
				
				+ "\nisbn: \"u9348984995898493\","
				+ "\npublishedDate: \"October 2005\""
				+ "\n}"
				+ "\n}";

		String expectedErrorMessage = "Error: Required Field Not Found: authors!";
		
		executeReadExpectingErrorMessage(bookJsonDataWithNoElementAuthors, expectedErrorMessage);;
	}
	
	
	
	/*@Test
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
		
	}*/

}
