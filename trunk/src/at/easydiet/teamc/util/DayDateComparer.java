package at.easydiet.teamc.util;

import java.util.Calendar;
import java.util.Date;

public class DayDateComparer {
	
	public static boolean compareDays(Date d1, Date d2){
		
		Calendar c1= Calendar.getInstance();
		Calendar c2=Calendar.getInstance();
		c1.setTimeInMillis(d1.getTime());
		c2.setTimeInMillis(d2.getTime());
		
		c1.set(Calendar.MILLISECOND, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.HOUR, 0);
		
		c2.set(Calendar.MILLISECOND, 0);
		c2.set(Calendar.SECOND, 0);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.HOUR, 0);
		
		if(c1.compareTo(c2)==0){
			return true;
		}
		return false;
	}

}
