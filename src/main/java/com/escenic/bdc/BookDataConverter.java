package main.java.com.escenic.bdc;

import java.io.FileNotFoundException;
import java.io.IOException;

import main.java.com.escenic.bdc.exception.FileEmptyException;
import main.java.com.escenic.bdc.exception.InvalidBookTextDataException;
import main.java.com.escenic.bdc.exception.InvalidBookXmlDataException;
import main.java.com.escenic.bdc.exception.InvalidInputArgumentException;
import main.java.com.escenic.bdc.exception.InvalidOutputFormatException;
import main.java.com.escenic.bdc.jaxb.beans.Book;
import main.java.com.escenic.bdc.reader.BookDataReader;
import main.java.com.escenic.bdc.reader.BookDataReaderFactory;
import main.java.com.escenic.bdc.util.BookDataUtil;
import main.java.com.escenic.bdc.writer.BookDataWriter;
import main.java.com.escenic.bdc.writer.BookDataWriterFactory;

import com.google.gson.JsonSyntaxException;

public class BookDataConverter {
	
	BookDataReader bookDataReader = null;
	BookDataWriter bookDataWriter = null;

	public static void main(String[] arguments) {
		
		System.out.println("------ Book Data Converter -------");

		convertBookData(arguments);
	}
	
	public static void convertBookData(String[] arguments) {
		String fileName = null;
		String outputFormat = null;
		try {
			// check if the command line input arguments are valid or not
			if(BookDataUtil.isValidInputArguments(arguments)) {
				fileName = arguments[0];
				outputFormat = arguments[1];

				// get the book data in string from the file
				String bookData = BookDataUtil.getBookDataAsString(fileName);
				
				// get the book data format that is provided in the file
				String bookDataFormat = BookDataUtil.getBookDataFormat(bookData);
				
				// check if the book data format in the desired output format is same
				// if same then print the output
				if (bookDataFormat.equalsIgnoreCase(outputFormat)) {
					System.out.println("Book data format in file and output format are same. Printing book data...");
					System.out.println(bookData);
				} else {
					// get the book data reader according to the data format
					BookDataReaderFactory readerFactory = new BookDataReaderFactory();
					BookDataReader reader = readerFactory.getBookDataReader(bookDataFormat);
					
					System.out.println("Reading book data...");
					Book book = reader.read(bookData);
					
					// get the book data writer according to the desired output format
					BookDataWriterFactory writerFactory = new BookDataWriterFactory();
					BookDataWriter writer = writerFactory.getBookDataWriter(outputFormat);
					
					System.out.println("Converting to " + outputFormat + " format...");
					
					String bookDataString = writer.write(book);
					
					// store book data in a file in text format
					BookDataUtil.storeBookDataInFile(book);
					
					System.out.println("Here is the output... ");
					System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
					System.out.println(bookDataString);
					System.out.println("---------------------------------------------");
					System.out.println("Book Name: " + book.getName().toUpperCase());
					System.out.println("Book data saved in Book_output.txt file");				
					
				}
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
	}
	
}
