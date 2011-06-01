package at.easydiet.teamc.web;

import at.easydiet.teamc.util.DayDateComparerTest;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(WebControllerTest.class);
		suite.addTestSuite(DayDateComparerTest.class);
		//$JUnit-END$
		return suite;
	}

}
