package test.java.com.escenic.bdc.reader;

import static org.junit.Assert.*;

import java.util.Arrays;

import main.java.com.escenic.bdc.exception.InvalidBookXmlDataException;
import main.java.com.escenic.bdc.jaxb.beans.Authors;
import main.java.com.escenic.bdc.jaxb.beans.Book;
import main.java.com.escenic.bdc.reader.BookDataReader;
import main.java.com.escenic.bdc.reader.BookDataReaderFactory;
import main.java.com.escenic.bdc.util.ApplicationConstants;

import org.junit.Test;

public class BookXmlDataReaderTest {

	String validBookXmlData = 
			"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
			+ "\n<book>"
			+ "\n<name>Effective Java</name>"
			+ "\n<authors>"
			+ "\n<author>Joshua Bloch</author>"
			+ "\n<author>James gosling</author>"
			+ "\n</authors>"
			+ "\n<publishedDate>October 2005</publishedDate>"
			+ "\n</book>";
	
	@Test (expected = InvalidBookXmlDataException.class)
	public void testReadWithMissingElementBook() throws Exception {
		
		String bookXmlDataWithMissingElementBook = 
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
				
				+ "\n<name>Effective Java</name>"
				+ "\n<authors>"
				+ "\n<author>Joshua Bloch</author>"
				+ "\n<author>James gosling</author>"
				+ "\n</authors>"
				+ "\n<publishedDate>October 2005</publishedDate>"
				+ "\n</book>";
		BookDataReaderFactory readerFactory = new BookDataReaderFactory();
		BookDataReader reader = readerFactory.getBookDataReader(ApplicationConstants.BOOK_DATA_FORMAT_XML);
		reader.read(bookXmlDataWithMissingElementBook);
	}
	
	@Test (expected = InvalidBookXmlDataException.class)
	public void testReadWithMissingElementName() throws Exception {
		
		String bookXmlDataWithMissingElementName = 
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
				+ "\n<book>"
				
				+ "\n<authors>"
				+ "\n<author>Joshua Bloch</author>"
				+ "\n<author>James gosling</author>"
				+ "\n</authors>"
				+ "\n<publishedDate>October 2005</publishedDate>"
				+ "\n</book>";
		BookDataReaderFactory readerFactory = new BookDataReaderFactory();
		BookDataReader reader = readerFactory.getBookDataReader(ApplicationConstants.BOOK_DATA_FORMAT_XML);
		reader.read(bookXmlDataWithMissingElementName);
	}
	
	@Test (expected = InvalidBookXmlDataException.class)
	public void testReadWithMissingElementAuthors() throws Exception {
		
		String bookXmlDataWithMissingElementAuthors = 
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
				+ "\n<book>"
				+ "\n<name>Effective Java</name>"
				
				+ "\n<author>Joshua Bloch</author>"
				+ "\n<author>James gosling</author>"
				+ "\n</authors>"
				+ "\n<publishedDate>October 2005</publishedDate>"
				+ "\n</book>";
		BookDataReaderFactory readerFactory = new BookDataReaderFactory();
		BookDataReader reader = readerFactory.getBookDataReader(ApplicationConstants.BOOK_DATA_FORMAT_XML);
		reader.read(bookXmlDataWithMissingElementAuthors);
	}
	
	@Test (expected = InvalidBookXmlDataException.class)
	public void testReadWithMissingElementAuthor() throws Exception {
		
		String bookXmlDataWithMissingElementAuthor = 
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
				+ "\n<book>"
				+ "\n<name>Effective Java</name>"
				+ "\n<authors>"

				+ "\n</authors>"
				+ "\n<publishedDate>October 2005</publishedDate>"
				+ "\n</book>";
		BookDataReaderFactory readerFactory = new BookDataReaderFactory();
		BookDataReader reader = readerFactory.getBookDataReader(ApplicationConstants.BOOK_DATA_FORMAT_XML);
		reader.read(bookXmlDataWithMissingElementAuthor);
	}
	
	@Test (expected = InvalidBookXmlDataException.class)
	public void testReadWithMissingElementPublishedDate() throws Exception {
		
		String bookXmlDataWithMissingElementPublishedDate = 
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
				+ "\n<book>"
				+ "\n<name>Effective Java</name>"
				+ "\n<authors>"
				+ "\n<author>Joshua Bloch</author>"
				+ "\n<author>James gosling</author>"
				+ "\n</authors>"
				
				+ "\n</book>";
		BookDataReaderFactory readerFactory = new BookDataReaderFactory();
		BookDataReader reader = readerFactory.getBookDataReader(ApplicationConstants.BOOK_DATA_FORMAT_XML);
		reader.read(bookXmlDataWithMissingElementPublishedDate);
	}
	
	@Test (expected = InvalidBookXmlDataException.class)
	public void testReadWithWrongTag() throws Exception {
		
		String bookXmlDataWithWrongTag = 
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
				+ "\n<WrongTag>"
				+ "\n<name>Effective Java</name>"
				+ "\n<authors>"
				+ "\n<author>Joshua Bloch</author>"
				+ "\n<author>James gosling</author>"
				+ "\n</authors>"
				+ "\n<publishedDate>October 2005</publishedDate>"
				+ "\n</book>";
		BookDataReaderFactory readerFactory = new BookDataReaderFactory();
		BookDataReader reader = readerFactory.getBookDataReader(ApplicationConstants.BOOK_DATA_FORMAT_XML);
		reader.read(bookXmlDataWithWrongTag);
	}
	
	@Test (expected = InvalidBookXmlDataException.class)
	public void testReadWithNoElementValueName() throws Exception {
		
		String bookXmlDataWithNoElementValueName = 
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
				+ "\n<book>"
				+ "\n<name> </name>"
				+ "\n<authors>"
				+ "\n<author>Joshua Bloch</author>"
				+ "\n<author>James gosling</author>"
				+ "\n</authors>"
				+ "\n<publishedDate>October 2005</publishedDate>"
				+ "\n</book>";
		BookDataReaderFactory readerFactory = new BookDataReaderFactory();
		BookDataReader reader = readerFactory.getBookDataReader(ApplicationConstants.BOOK_DATA_FORMAT_XML);
		reader.read(bookXmlDataWithNoElementValueName);
	}
	
	@Test (expected = InvalidBookXmlDataException.class)
	public void testReadWithNoElementValueAuthor() throws Exception {
		
		String bookXmlDataWithNoElementValueAuthor = 
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
				+ "\n<book>"
				+ "\n<name>Effective Java</name>"
				+ "\n<authors>"
				+ "\n<author> </author>"
				+ "\n<author>James gosling</author>"
				+ "\n</authors>"
				+ "\n<publishedDate>October 2005</publishedDate>"
				+ "\n</book>";
		BookDataReaderFactory readerFactory = new BookDataReaderFactory();
		BookDataReader reader = readerFactory.getBookDataReader(ApplicationConstants.BOOK_DATA_FORMAT_XML);
		reader.read(bookXmlDataWithNoElementValueAuthor);
	}
	
	@Test (expected = InvalidBookXmlDataException.class)
	public void testReadWithNoElementValuePublishedDate() throws Exception {
		
		String bookXmlDataWithNoElementValuePublishedDate = 
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
				+ "\n<book>"
				+ "\n<name>Effective Java</name>"
				+ "\n<authors>"
				+ "\n<author>Joshua Bloch</author>"
				+ "\n<author>James gosling</author>"
				+ "\n</authors>"
				+ "\n<publishedDate> </publishedDate>"
				+ "\n</book>";
		BookDataReaderFactory readerFactory = new BookDataReaderFactory();
		BookDataReader reader = readerFactory.getBookDataReader(ApplicationConstants.BOOK_DATA_FORMAT_XML);
		reader.read(bookXmlDataWithNoElementValuePublishedDate);
	}
	
	@Test
	public void testReadWithValidBookXmlData() throws Exception {
		
		String validBookXmlData = 
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
				+ "\n<book>"
				+ "\n<name>Effective Java</name>"
				+ "\n<authors>"
				+ "\n<author>Joshua Bloch</author>"
				+ "\n<author>James gosling</author>"
				+ "\n</authors>"
				+ "\n<isbn>u9348984995898493</isbn>"
				+ "\n<publishedDate>October 2005</publishedDate>"
				+ "\n</book>";
		
		Book mockBook = new Book();
		mockBook.setName("Effective Java");
		Authors authors = new Authors();
		authors.setAuthor(Arrays.asList("Joshua Bloch", "James gosling"));
		mockBook.setAuthors(authors);
		mockBook.setIsbn("u9348984995898493");
		mockBook.setPublishedDate("October 2005");
		
		BookDataReaderFactory readerFactory = new BookDataReaderFactory();
		BookDataReader reader = readerFactory.getBookDataReader(ApplicationConstants.BOOK_DATA_FORMAT_XML);
		Book book = reader.read(validBookXmlData);
		
		assertNotNull(book);
		assertTrue(mockBook.getName().equals(book.getName()));
		
		assertTrue(mockBook.getAuthors().getAuthor().containsAll(book.getAuthors().getAuthor()) && 
				book.getAuthors().getAuthor().containsAll(mockBook.getAuthors().getAuthor()));
		
		assertTrue(mockBook.getIsbn().equals(book.getIsbn()));
		assertTrue(mockBook.getPublishedDate().equals(book.getPublishedDate()));
	}
}
