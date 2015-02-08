package main.java.com.escenic.bdc.reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import main.java.com.escenic.bdc.exception.InvalidBookTextDataException;
import main.java.com.escenic.bdc.jaxb.beans.Authors;
import main.java.com.escenic.bdc.jaxb.beans.Book;
import main.java.com.escenic.bdc.json.beans.BookJson;
import main.java.com.escenic.bdc.util.ApplicationConstants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * Implementation of BookDataReader
 * Reads book data of json format from a file
 * @author Shajedul Islam
 *
 */
public class BookJsonDataReader implements BookDataReader {
	
	/**
	 * Reads book data as json from a file, parses it and creates book object out of it
	 * @param bookData 	book data in string
	 * @return	the book object that is created out of the json data
	 * @throws IOException 
	 * @throws InvalidBookTextDataException
	 */
	@Override
	public Book read(String bookData) throws JsonParseException {
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		
		// Create a deserializer that will validate json data elements
	    BookDataJsonDeserializer deserializer = new BookDataJsonDeserializer();
	    deserializer.registerRequiredField("name");
	    deserializer.registerRequiredField("authors");
	    deserializer.registerRequiredField("isbn");
	    deserializer.registerRequiredField("publishedDate");
	    gsonBuilder.registerTypeAdapter(BookJson.class, deserializer);
	    
	    Gson gson = gsonBuilder.create();
	    JsonElement element = new JsonParser().parse(bookData);
		JsonObject object = element.getAsJsonObject();
		object = object.getAsJsonObject("book");
		
		if (object != null) {
			BookJson bookJson = gson.fromJson(object, BookJson.class);			
			
			// Create a book object and set values into is from bookJson object
			Book book = prepareBookObject(bookJson);
			
			return book;
		} else {
			throw new JsonParseException("Error: Required Field Not Found: book!" );
		}
	}
	
	/**
	 * Prepares book object from bookJson object
	 * @param bookJson A temporary object parsed from the json data 
	 * @return book object
	 */
	private Book prepareBookObject(BookJson bookJson) {
		
		// Create a book object and set values into is from bookJson object
		Book book = new Book();
		book.setName(bookJson.getName());
		book.setIsbn(bookJson.getIsbn());
		book.setPublishedDate(bookJson.getPublishedDate());
		// set authors to book 
		Authors authors = new Authors();
		authors.setAuthor(bookJson.getAuthors());
		book.setAuthors(authors);
		
		return book;
	}
	
}
