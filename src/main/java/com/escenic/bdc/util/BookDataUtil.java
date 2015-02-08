package main.java.com.escenic.bdc.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import main.java.com.escenic.bdc.exception.FileEmptyException;
import main.java.com.escenic.bdc.exception.InvalidInputArgumentException;
import main.java.com.escenic.bdc.exception.InvalidOutputFormatException;
import main.java.com.escenic.bdc.jaxb.beans.Book;
import main.java.com.escenic.bdc.writer.BookDataWriter;
import main.java.com.escenic.bdc.writer.BookDataWriterFactory;

/**
 * A class that contains utility methods for book data
 * @author Shajedul Islam
 *
 */
public class BookDataUtil {
	
	private static Set<String> supportedOutputFormats = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER) {{
	    add("xml");
	    add("text");
	    add("json");
	}};

	/**
	 * Identifies the book data format in the file
	 * @param bookData	book data as string
	 * @return	book data format i.e. text/xml/json
	 */
	public static String getBookDataFormat(String bookData) throws FileEmptyException {
		
		String bookDataFormat = null;
		System.out.println("Guessing text format...");
		
		// Assuming that the encoding of that string is known (or is ASCII or UTF), 
		// then looking at the very first char of that String should be enough to check the format
		if (bookData.equals("")) {
			throw new FileEmptyException("Error: File does not contain any data!");
		} else if (bookData.startsWith("<")) {
			bookDataFormat = ApplicationConstants.BOOK_DATA_FORMAT_XML;
		} else if (bookData.startsWith("{")) {
			bookDataFormat = ApplicationConstants.BOOK_DATA_FORMAT_JSON;
		} else {
			bookDataFormat = ApplicationConstants.BOOK_DATA_FORMAT_TEXT;
		}
		
		System.out.println("Book data is in " + bookDataFormat + " format");
		return bookDataFormat;
	}
	
	/**
	 * Reads book data from the file and returns the data as string
	 * @param fileName	fileName given by the user
	 * @return	book data as string 
	 * @throws FileNotFoundExcep.tion 
	 */
	public static String getBookDataAsString(String fileName) throws IOException {

		System.out.println("Reading input...");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
		
		BufferedReader br = null;
		StringBuilder bookData = new StringBuilder();
        String line;
        
        try {

        	File file = new File(fileName);
			br = new BufferedReader(new FileReader(file));

			while ((line = br.readLine()) != null) {
				// trim the line
				line = line.trim();
				// omit any comma at the end of the line and trim the line
				/*if (line.endsWith(",")) {
					line = line.substring(0, line.length() - 1);					
				}*/
				bookData.append(line).append("\n");
			}
			br.close();
        } catch (IOException ex) {
        	throw new IOException("Error occured while reading book data!");
		} 
        
        System.out.println(bookData.toString().trim());
        System.out.println("---------------------------------------------");
        
        return bookData.toString().trim();
        
	}
	
	/**
	 * Validates the input arguments.
	 * checks if the user has entered two command line arguments.
	 * checks if the file of the given name exists or not.
	 * check if the output format that the client desire to see the book data is valid or not.
	 * @param arguments command line input arguments
	 * @return true if the format is supported by the program, otherwise false
	 */
	public static boolean isValidInputArguments(String[] arguments) throws InvalidInputArgumentException, 
																		InvalidOutputFormatException, 
																		FileNotFoundException {
		
		String fileName = null;
		String outputFormat = null;
		
		if (arguments.length < 2) {
			throw new InvalidInputArgumentException("Please enter a file name and a desired output format");
		} else {
			fileName = arguments[0];
			outputFormat = arguments[1];	
			
			// Check if the file with the given name exists or not
			File file = new File(fileName);
			if(!file.exists() && !file.isDirectory()) {
				throw new FileNotFoundException("Error: File " + fileName + " not found!");
			}
			
			// Check if the output format is supported or not
			if (!supportedOutputFormats.contains(outputFormat)) {
				throw new InvalidOutputFormatException("Error: wrong output format: " + outputFormat + "\nSupported formats: xml/text/json");
			}
			/*if (!outputFormat.equalsIgnoreCase(ApplicationConstants.BOOK_DATA_FORMAT_TEXT) && 
					!outputFormat.equalsIgnoreCase(ApplicationConstants.BOOK_DATA_FORMAT_XML) && 
					!outputFormat.equalsIgnoreCase(ApplicationConstants.BOOK_DATA_FORMAT_JSON)) {
				
				throw new InvalidOutputFormatException("Error: wrong output format: " + outputFormat + "\nSupported formats: xml/text");
			}*/ 
			
			return true;
		}
	}
	
	public static void storeBookDataInFile(Book book) {
			
		String newLine = ""; 		
		try {
			File file = new File("Book_output.txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
				newLine = "";
			} else {
				newLine = System.getProperty("line.separator");
			}
 
			BookDataWriterFactory writerFactory = new BookDataWriterFactory();
			BookDataWriter writer = writerFactory.getBookDataWriter(ApplicationConstants.BOOK_DATA_FORMAT_TEXT);
			String bookData = writer.write(book);
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(newLine + newLine +  bookData);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
