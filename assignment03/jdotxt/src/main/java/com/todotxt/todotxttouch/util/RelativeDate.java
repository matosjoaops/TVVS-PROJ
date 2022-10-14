/**
 * This file is part of Todo.txt Touch, an Android app for managing your todo.txt file (http://todotxt.com).
 * 
 * Thanks to: http://kurtischiappone.com/programming/java/relative-date
 */
package com.todotxt.todotxttouch.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.chschmid.jdotxt.gui.JdotxtGUI;

public class RelativeDate {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	//private static Context context = TodoApplication.getAppContetxt();

	// Doesn't handle leap year, etc, but we don't need to be very
	// accurate. This is just for human readable date displays.
	private static final long SECOND = 1000; //milliseconds
	private static final long HOUR = 3600 * SECOND;
	private static final long DAY = 24 * HOUR;
	private static final long YEAR = 365 * DAY;

	/**
	 * This method returns a String representing the relative date by comparing
	 * the Calendar being passed in to the date / time that it is right now.
	 * 
	 * @param calendar
	 * @return String representing the relative date
	 */

	public static String getRelativeDate(Calendar calendar) {
		Calendar today = new GregorianCalendar();
		today.set(GregorianCalendar.HOUR_OF_DAY, 0);
		today.set(GregorianCalendar.MINUTE, 0);
		today.set(GregorianCalendar.SECOND, 0);
		today.set(GregorianCalendar.MILLISECOND,0);
		
		return getRelativeDate(today, calendar);
	}
	
	public static String getRelativeDate(Calendar d1, Calendar d2) {
		long diff = d1.getTimeInMillis() - d2.getTimeInMillis();

		if (diff < 0 || diff >= RelativeDate.YEAR) {
			// future or far in past,
			// just return yyyy-mm-dd
			return sdf.format(d2.getTime());
		}

		if (diff >= 60 * RelativeDate.DAY) {
			// N months ago
			long months = diff / (30 * DAY);
			return String.format(JdotxtGUI.lang.getWord("dates_months_ago"), months);
		}

		if (diff >= 30 * RelativeDate.DAY) {
			// 1 month ago
			return JdotxtGUI.lang.getWord("dates_one_month_ago");
		}

		if (diff >= 2 * RelativeDate.DAY) {
			// more than 2 days ago
			long days = diff / DAY;
			return String.format(JdotxtGUI.lang.getWord("dates_days_ago"), days);
		}

		if (diff >= 1 * RelativeDate.DAY) {
			// 1 day ago
			return JdotxtGUI.lang.getWord("dates_one_day_ago");
		}

		// today
		return JdotxtGUI.lang.getWord("dates_today");
	}

	/**
	 * This method returns a String representing the relative date by comparing
	 * the Date being passed in to the date / time that it is right now.
	 * 
	 * @param date
	 * @return String representing the relative date
	 */
	public static String getRelativeDate(Date date) {
		Calendar converted = GregorianCalendar.getInstance();
		converted.setTime(date);
		return getRelativeDate(converted);
	}

}