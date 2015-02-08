package test.java.com.escenic.bdc.writer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import main.java.com.escenic.bdc.util.ApplicationConstants;
import main.java.com.escenic.bdc.writer.BookDataWriter;
import main.java.com.escenic.bdc.writer.BookDataWriterFactory;
import main.java.com.escenic.bdc.writer.BookTextDataWriter;
import main.java.com.escenic.bdc.writer.BookXmlDataWriter;

import org.junit.Test;

public class BookDataWriterFactoryTest {

	@Test
	public void testGetBookDataWriterWithTextFormat() {
		// Test the book data Writer implementation for text format
		BookDataWriterFactory WriterFactory = new BookDataWriterFactory();
		BookDataWriter Writer = WriterFactory.getBookDataWriter(ApplicationConstants.BOOK_DATA_FORMAT_TEXT);
		assertNotNull(Writer);
		assertTrue(Writer instanceof BookTextDataWriter);
	}
	
	@Test
	public void testGetBookDataWriterWithXmlFormat() {
		// Test the book data Writer implementation for xml format
		BookDataWriterFactory WriterFactory = new BookDataWriterFactory();
		BookDataWriter Writer = WriterFactory.getBookDataWriter(ApplicationConstants.BOOK_DATA_FORMAT_XML);
		assertNotNull(Writer);
		assertTrue(Writer instanceof BookXmlDataWriter);
	}
}
