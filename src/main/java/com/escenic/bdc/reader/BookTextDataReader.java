package main.java.com.escenic.bdc.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import main.java.com.escenic.bdc.exception.InvalidBookTextDataException;
import main.java.com.escenic.bdc.jaxb.beans.Authors;
import main.java.com.escenic.bdc.jaxb.beans.Book;
import main.java.com.escenic.bdc.util.ApplicationConstants;

/**
 * Implementation of BookDataReader
 * Reads book data of text format from a file
 * @author Shajedul Islam
 *
 */
public class BookTextDataReader implements BookDataReader {
	
	private Map<String, String> bookDataMap;
	// A set that contains valid input keys of a book data; case insensitive
	private Set<String> validInputKeys = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER) {{
	    add("name");
	    add("authors");
	    add("isbn");
	    add("published date");
	}};
	

	/**
	 * Reads book data as text from a file, parses it and creates book object out of it
	 * @param bookData 	book data in string
	 * @return	the book object that is created out of the text data
	 * @throws IOException 
	 * @throws InvalidBookTextDataException 
	 */
	@Override
	public Book read(String bookData) throws IOException, InvalidBookTextDataException {
				
		bookDataMap = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
		String line = "";
		BufferedReader br = new BufferedReader(new StringReader(bookData));
		try {
			while ((line = br.readLine()) != null) {
				
				// trim the line and see if it is blank or not
				line = line.trim();
				if (line.length() > 0) {
					// check if the line contains a key value pair with a colon
					// as separator
					String[] newLine = line.split(":");

					if (newLine.length != 2) {
						throw new InvalidBookTextDataException("Error: Book text data not valid. Please provide \":\" separated key value pair!");
					}

					String key = newLine[0];
					String value = newLine[1];

					bookDataMap.put(key, value.trim());
				}
			}
			
			/*
			 * validate keys in the book data file
			 * check if the keys are name, authors, published date 
			 */
			if (isValidInputKeys(bookDataMap)) {
				
				// prepare book object
				Book book = prepareBookObject(bookDataMap);
				return book;
			}
			
		} catch (IOException e) {
			throw new IOException("Error occured while reading book data!");
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw new InvalidBookTextDataException("Error: Book text data not valid. Please provide proper data in the file!");
		}
		
		return null;
	}
	
	/**
	 * Prepares book object from book data map
	 * @param bookDataMap Map that contains book data as key value pair 
	 * @return book object
	 */
	private Book prepareBookObject(Map<String, String> bookDataMap) {
		
		Book book = new Book();
		
		// set book name
		book.setName(bookDataMap.get(ApplicationConstants.BOOK_DATA_NAME));
		
		// get comma separated book authors from map and add into a list
		String authorString = bookDataMap.get(ApplicationConstants.BOOK_DATA_AUTHOR);
		String[] authorStringSplitted = authorString.split(",");
		
		List<String> authorList = new ArrayList<String>();
		// trim and add author into a list
		for (String author : authorStringSplitted) {
			authorList.add(author.trim());
		}
		
		// set authors
		Authors authors = new Authors();
		authors.setAuthor(authorList);
		book.setAuthors(authors);
		
		// set book isbn
		book.setIsbn(bookDataMap.get(ApplicationConstants.BOOK_DATA_ISBN));
		
		// set book published date
		book.setPublishedDate(bookDataMap.get(ApplicationConstants.BOOK_DATA_PUBLISHED_DATE));
		
		return book;
	}
	
	/**
	 * validates the book data keys; name, authors, isbn, published date
	 * @param bookDataMap
	 * @return
	 * @throws InvalidBookTextDataException
	 */
	private boolean isValidInputKeys(Map<String, String> bookDataMap) throws InvalidBookTextDataException {
		
		// make a shallow copy of book data map so that the values don't get altered during validation
		Map<String, String> shallowCopyOfBookDataMap = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
		shallowCopyOfBookDataMap.putAll(bookDataMap);
		Set<String> inputKeys = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER); 
		inputKeys = shallowCopyOfBookDataMap.keySet();
		
		// find the difference between valid input keys and user input keys
		List<String> missingInputKeys = new ArrayList<String>();
		for (String validInputKey: validInputKeys) {
			boolean isRemoved = inputKeys.remove(validInputKey);
			if (!isRemoved) {
				missingInputKeys.add(validInputKey);
			}
		}
		
		/*if (missingInputKeys.size() > 0) {
			String errorMessage = "Error: Book text data not valid. Following input keys missing: ";
			String missingInputKeysString = "";
			for (String misingInputKey : missingInputKeys) {
				missingInputKeysString += misingInputKey;
				// add a "," after the key if there are more missing keys
				missingInputKeys.remove(misingInputKey);
				missingInputKeysString += (missingInputKeys.size() > 0) ? ", " : "";
			}
			throw new InvalidBookTextDataException(errorMessage + missingInputKeysString);
		} else {
			return true;
		}*/
		
		if (missingInputKeys.size() > 0) {
			String errorMessage = "Error: Book text data not valid. Following input keys missing: ";
			StringBuilder missingInputKeysString = new StringBuilder();
			
			String delimeter = "";
			for (String misingInputKey : missingInputKeys) {
				missingInputKeysString.append(delimeter).append(misingInputKey);
			    delimeter = ", ";
			}
			
			throw new InvalidBookTextDataException(errorMessage + missingInputKeysString);
		} else {
			return true;
		}
		
		/*if (inputKeys.size() > 0) {
			String errorMessage = "Error: Book text data not valid. Following input keys not supported: ";
			String invalidInputKeys = "";
			for (String inputKey : inputKeys) {
				invalidInputKeys += inputKey + ", ";
			}
			throw new InvalidBookTextDataException(errorMessage + invalidInputKeys);
		} else {
			return true;
		}*/
	}
	
}
