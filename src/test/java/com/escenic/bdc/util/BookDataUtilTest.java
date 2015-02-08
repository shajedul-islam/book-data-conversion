package test.java.com.escenic.bdc.util;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import main.java.com.escenic.bdc.exception.FileEmptyException;
import main.java.com.escenic.bdc.exception.InvalidInputArgumentException;
import main.java.com.escenic.bdc.exception.InvalidOutputFormatException;
import main.java.com.escenic.bdc.util.ApplicationConstants;
import main.java.com.escenic.bdc.util.BookDataUtil;

import org.junit.Before;
import org.junit.Test;

public class BookDataUtilTest {

	String bookTextDataExpected = "name: Effective Java\nauthors: Joshua Bloch, James gosling\nisbn: u9348984995898493\npublished date: October 2005";
	
	String bookJsonDataExpected = "{"
								+ "\nbook: {"
								+ "\nname: \"Effective Java\","
								+ "\nauthors: [\"Joshua Bloch\", \"James Gosling\"],"
								+ "\nisbn: \"u9348984995898493\","
								+ "\npublishedDate: \"October 2005\""
								+ "\n}"
								+ "\n}";
	
	String bookXmlDataExpected = 
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
	
	@Test (expected = InvalidInputArgumentException.class)
	public void testIsValidInputArgumentsWithInvalidInputArgument() throws FileNotFoundException, InvalidInputArgumentException, InvalidOutputFormatException {
		// Giving only file name, not the output format
		String[] arguments = {"Book_text.txt"};
		BookDataUtil.isValidInputArguments(arguments);
	}
	
	@Test (expected = InvalidOutputFormatException.class)
	public void testIsValidInputArgumentsWithInvalidOutputFormat() throws FileNotFoundException, InvalidInputArgumentException, InvalidOutputFormatException {
		// Supported output formats are: text/xml/json
		String[] arguments = {"Book_text.txt", "string"};
		BookDataUtil.isValidInputArguments(arguments);
	}
	
	@Test (expected = FileNotFoundException.class)
	public void testIsValidInputArgumentsWithInvalidFileName() throws FileNotFoundException, InvalidInputArgumentException, InvalidOutputFormatException {
		// Enter a file name that does not exist
		String[] arguments = {"NoBook.txt", "text"};
		BookDataUtil.isValidInputArguments(arguments);
	}
	
	@Test
	public void testIsValidInputArgumentsWithValidInputArguments() throws FileNotFoundException, InvalidInputArgumentException, InvalidOutputFormatException {
		// Enter a valid file name and output format
		String[] arguments1 = {"Book_text.txt", "xml"};
		assertTrue(BookDataUtil.isValidInputArguments(arguments1));
		
		String[] arguments2 = {"Book_text.txt", "text"};
		assertTrue(BookDataUtil.isValidInputArguments(arguments2));
		
		String[] arguments3 = {"Book_text.txt", "json"};
		assertTrue(BookDataUtil.isValidInputArguments(arguments3));
	}
	
	@Test
	public void testGetBookDataAsStringFromTextData() throws IOException {

		// Read a file that contains text data
		String bookTextDataActual = BookDataUtil.getBookDataAsString("Book_text.txt");
		assertTrue(bookTextDataExpected.equalsIgnoreCase(bookTextDataActual));
	}
	
	@Test
	public void testGetBookDataAsStringFromXmlData() throws IOException {

		// Read a file that contains xml data
		String bookXmlDataActual = BookDataUtil.getBookDataAsString("Book_xml.txt");
		assertEquals(bookXmlDataExpected, bookXmlDataActual);
	}
	
	@Test
	public void testGetBookDataAsStringFromJsonData() throws IOException {

		// Read a file that contains xml data
		String bookJsonDataActual = BookDataUtil.getBookDataAsString("Book_json.txt");
		assertEquals(bookJsonDataExpected, bookJsonDataActual);
	}
	
	@Test (expected = FileEmptyException.class)
	public void testGetBookDataFormatFromEmptyString() throws FileEmptyException, IOException {
		// Try to get data format of a empty string
		BookDataUtil.getBookDataFormat("");
	}
	
	@Test
	public void testGetBookDataFormatFromTextData() throws FileEmptyException {
		// Try to get data format of a empty string
		String bookDataFormatActual = BookDataUtil.getBookDataFormat(bookTextDataExpected);
		assertEquals(bookDataFormatActual, ApplicationConstants.BOOK_DATA_FORMAT_TEXT);
	}
	
	@Test
	public void testGetBookDataFormatFromXmlData() throws FileEmptyException {
		// Try to get data format of a empty string
		String bookDataFormatActual = BookDataUtil.getBookDataFormat(bookXmlDataExpected);
		assertEquals(bookDataFormatActual, ApplicationConstants.BOOK_DATA_FORMAT_XML);
	}
	
	@Test
	public void testGetBookDataFormatFromJsonData() throws FileEmptyException {
		// Try to get data format of a empty string
		String bookDataFormatActual = BookDataUtil.getBookDataFormat(bookJsonDataExpected);
		assertEquals(bookDataFormatActual, ApplicationConstants.BOOK_DATA_FORMAT_JSON);
	}
}
