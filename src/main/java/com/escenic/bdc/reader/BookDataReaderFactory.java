package main.java.com.escenic.bdc.reader;

import main.java.com.escenic.bdc.util.ApplicationConstants;

/**
 * Factory pattern class to encapsulate the detail of determining which book data reader class to instantiate
 * @author Shajedul Islam
 *
 */
public class BookDataReaderFactory {

	/**
	 * Gets particular book data Reader object based on the input format
	 * @return
	 */
	public BookDataReader getBookDataReader(String bookDataFormat) {
		
		if (bookDataFormat == null) {
			return null;
			
		} else if (bookDataFormat.equals(ApplicationConstants.BOOK_DATA_FORMAT_TEXT)) {
			return new BookTextDataReader();
			
		} else if (bookDataFormat.equals(ApplicationConstants.BOOK_DATA_FORMAT_JSON)) {
			return new BookJsonDataReader();
			
		} else if (bookDataFormat.equals(ApplicationConstants.BOOK_DATA_FORMAT_XML)) {
			return new BookXmlDataReader();
			
		}
		
		return null;
	}
}
