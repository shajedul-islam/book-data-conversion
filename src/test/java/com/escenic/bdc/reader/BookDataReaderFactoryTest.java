package test.java.com.escenic.bdc.reader;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import main.java.com.escenic.bdc.reader.BookDataReader;
import main.java.com.escenic.bdc.reader.BookDataReaderFactory;
import main.java.com.escenic.bdc.reader.BookTextDataReader;
import main.java.com.escenic.bdc.reader.BookXmlDataReader;
import main.java.com.escenic.bdc.util.ApplicationConstants;

import org.junit.Test;

public class BookDataReaderFactoryTest {

	@Test
	public void testGetBookDataReaderWithTextFormat() {
		// Test the book data reader implementation for text format
		BookDataReaderFactory readerFactory = new BookDataReaderFactory();
		BookDataReader reader = readerFactory.getBookDataReader(ApplicationConstants.BOOK_DATA_FORMAT_TEXT);
		assertNotNull(reader);
		assertTrue(reader instanceof BookTextDataReader);
	}
	
	@Test
	public void testGetBookDataReaderWithXmlFormat() {
		// Test the book data reader implementation for xml format
		BookDataReaderFactory readerFactory = new BookDataReaderFactory();
		BookDataReader reader = readerFactory.getBookDataReader(ApplicationConstants.BOOK_DATA_FORMAT_XML);
		assertNotNull(reader);
		assertTrue(reader instanceof BookXmlDataReader);
	}

}
