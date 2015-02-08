package main.java.com.escenic.bdc.writer;

import main.java.com.escenic.bdc.util.ApplicationConstants;

/**
 * Factory pattern class to encapsulate the detail of determining which book data writer class to instantiate 
 * @author Shajedul Islam
 *
 */
public class BookDataWriterFactory {

	/**
	 * Gets particular book data writer object based on the desired output format
	 * @return
	 */
	public BookDataWriter getBookDataWriter(String outputFormat) {
		
		if (outputFormat == null) {
			return null;
			
		} else if (outputFormat.equals(ApplicationConstants.BOOK_DATA_FORMAT_TEXT)) {
			return new BookTextDataWriter();
			
		} else if (outputFormat.equals(ApplicationConstants.BOOK_DATA_FORMAT_XML)) {
			return new BookXmlDataWriter();
			
		} else if (outputFormat.equals(ApplicationConstants.BOOK_DATA_FORMAT_JSON)) {
			return new BookJsonDataWriter();
			
		} 
		
		return null;
	}
}
