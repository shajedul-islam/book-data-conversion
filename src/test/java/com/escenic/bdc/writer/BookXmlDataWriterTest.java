package test.java.com.escenic.bdc.writer;

import static org.junit.Assert.*;

import java.util.Arrays;

import main.java.com.escenic.bdc.jaxb.beans.Authors;
import main.java.com.escenic.bdc.jaxb.beans.Book;
import main.java.com.escenic.bdc.util.ApplicationConstants;
import main.java.com.escenic.bdc.writer.BookDataWriter;
import main.java.com.escenic.bdc.writer.BookDataWriterFactory;

import org.junit.Test;

public class BookXmlDataWriterTest {

	@Test
	public void testWrite() {
		
		String bookXmlDataExpected = 
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
				+ "\n<book>"
				+ "\n    <name>Effective Java</name>"
				+ "\n    <authors>"
				+ "\n        <author>Joshua Bloch</author>"
				+ "\n        <author>James gosling</author>"
				+ "\n    </authors>"
				+ "\n    <publishedDate>October 2005</publishedDate>"
				+ "\n</book>\n";
		
		Book mockBook = new Book();
		mockBook.setName("Effective Java");
		Authors authors = new Authors();
		authors.setAuthor(Arrays.asList("Joshua Bloch", "James gosling"));
		mockBook.setAuthors(authors);
		mockBook.setPublishedDate("October 2005");
		
		BookDataWriterFactory WriterFactory = new BookDataWriterFactory();
		BookDataWriter writer = WriterFactory.getBookDataWriter(ApplicationConstants.BOOK_DATA_FORMAT_XML);
		String bookXmlDataActual = writer.write(mockBook);
		
		assertTrue(bookXmlDataExpected.equalsIgnoreCase(bookXmlDataActual));
	}

}
