package at.easydiet.teamc.util;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class DayDateComparerTest extends TestCase {
	
	//instance variables
	private Date d1;
	private Date d2;
	private Date d3;
	
	@Before
	public void setUp() throws Exception {
		Calendar c = Calendar.getInstance();
		c.set(1982, 2, 4, 19, 30, 0);
		d1 = new Date(c.getTime().getTime());
		d2 = new Date(d1.getTime());
		c.set(1982, 2, 4, 9, 30, 2);
		d3 = new Date(c.getTime().getTime());
	}

	@Test
	public void testCompareDays() {
		assertTrue(DayDateComparer.compareDays(d1, d2));
		assertFalse(DayDateComparer.compareDays(d1, d3));
	}

}
