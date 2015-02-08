package test.java.com.escenic.bdc.writer;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import main.java.com.escenic.bdc.jaxb.beans.Authors;
import main.java.com.escenic.bdc.jaxb.beans.Book;
import main.java.com.escenic.bdc.util.ApplicationConstants;
import main.java.com.escenic.bdc.writer.BookDataWriter;
import main.java.com.escenic.bdc.writer.BookDataWriterFactory;

import org.junit.Test;

public class BookTextDataWriterTest {

	@Test
	public void testWrite() {
		
		String bookTextDataExpected = "name: Effective Java\nauthors: Joshua Bloch, James gosling\nisbn: u9348984995898493\npublished date: October 2005";
		
		Book mockBook = new Book();
		mockBook.setName("Effective Java");
		Authors authors = new Authors();
		authors.setAuthor(Arrays.asList("Joshua Bloch", "James gosling"));
		mockBook.setAuthors(authors);
		mockBook.setIsbn("u9348984995898493");
		mockBook.setPublishedDate("October 2005");
		
		BookDataWriterFactory WriterFactory = new BookDataWriterFactory();
		BookDataWriter writer = WriterFactory.getBookDataWriter(ApplicationConstants.BOOK_DATA_FORMAT_TEXT);
		String bookTextDataActual = writer.write(mockBook);
		
		assertTrue(bookTextDataExpected.equalsIgnoreCase(bookTextDataActual));
	}

}
