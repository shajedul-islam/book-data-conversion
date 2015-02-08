package main.java.com.escenic.bdc.writer;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import main.java.com.escenic.bdc.jaxb.beans.Book;
import main.java.com.escenic.bdc.jaxb.beans.ObjectFactory;

/**
 * Implementation of BookDataWriter
 * Writes book data in plain text format
 * @author Shajedul Islam
 *
 */
public class BookXmlDataWriter implements BookDataWriter {

	/**
	 * Writes book data in xml format
	 * @param book the book object parsed from the file
	 */
	@Override
	public String write(Book book) {
		
		JAXBContext context;
		try {
			ObjectFactory factory = new ObjectFactory();
			context = JAXBContext.newInstance("main.java.com.escenic.bdc.jaxb.beans");
			JAXBElement<Book> element = factory.createBook(book);
			
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			StringWriter writer = new StringWriter();
			m.marshal(element, writer);
			
			return writer.toString();
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
