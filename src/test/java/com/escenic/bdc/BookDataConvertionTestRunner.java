package test.java.com.escenic.bdc;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class BookDataConvertionTestRunner {

	public static void main(String[] args) {

		Result result = JUnitCore.runClasses(BookDataConversionAllTests.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
	}
}
