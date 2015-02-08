package test.java.com.escenic.bdc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.java.com.escenic.bdc.reader.BookDataReaderFactoryTest;
import test.java.com.escenic.bdc.reader.BookTextDataReaderTest;
import test.java.com.escenic.bdc.reader.BookXmlDataReaderTest;
import test.java.com.escenic.bdc.util.BookDataUtilTest;
import test.java.com.escenic.bdc.writer.BookDataWriterFactoryTest;
import test.java.com.escenic.bdc.writer.BookTextDataWriterTest;
import test.java.com.escenic.bdc.writer.BookXmlDataWriterTest;

@RunWith(Suite.class)
@SuiteClasses({ BookDataUtilTest.class, 
				BookDataReaderFactoryTest.class, 
				BookTextDataReaderTest.class, 
				BookXmlDataReaderTest.class,
				BookDataWriterFactoryTest.class, 
				BookTextDataWriterTest.class, 
				BookXmlDataWriterTest.class,
				})
public class BookDataConversionAllTests {

}
