package main.java.com.escenic.bdc.reader;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import main.java.com.escenic.bdc.exception.InvalidBookXmlDataException;
import main.java.com.escenic.bdc.jaxb.beans.Book;
import main.java.com.escenic.bdc.jaxb.beans.ObjectFactory;

import org.xml.sax.SAXException;

/**
 * Implementation of BookDataReader
 * Reads book data of xml format from a file
 * @author Shajedul Islam
 *
 */
public class BookXmlDataReader implements BookDataReader {

	/**
	 * Reads book data as xml from a file, parses it and creates book object out of it
	 * @param bookData 	book data in string
	 * @return	the book object that is created out of the xml data
	 * @throws UnmarshalException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	@Override
	public Book read(String bookData) throws IOException, InvalidBookXmlDataException {

		try {
			// validate input xml according to the xml schema 
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            //Schema schema = factory.newSchema(new File("xsd/book.xsd"));
            Source schemaFile = new StreamSource(getClass().getClassLoader().getResourceAsStream("xsd/book.xsd"));
            Schema schema = factory.newSchema(schemaFile);
            
            
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new ByteArrayInputStream(bookData.getBytes())));
			
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
	        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

	        StringReader reader = new StringReader(bookData);
	        JAXBElement<Book> unmarshalledObject = (JAXBElement<Book>) unmarshaller.unmarshal(reader);
	        Book book = unmarshalledObject.getValue();
			
	        return book;
			
		} catch (JAXBException | SAXException e) {
            throw new InvalidBookXmlDataException("Error: " + e.getMessage());
        } catch (IOException ex) {
        	throw new IOException("Error occured while reading book data!");
		} 
	}

}
