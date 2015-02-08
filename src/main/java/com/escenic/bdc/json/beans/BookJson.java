package main.java.com.escenic.bdc.json.beans;

import java.util.List;

/**
 * A temporary class that will represent the json structure of book data
 * Due to incompatibility of xsd generated JAXB Book class
 * @author Shajedul Islam
 *
 */
public class BookJson {

	private String name;
	private List<String> authors;
	private String isbn;
	private String publishedDate;
	public List<String> getAuthors() {
		return authors;
	}
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}
	
	
}
