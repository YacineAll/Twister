package com.twister.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Tools {
	
	
	private static  final GregorianCalendar gc = new java.util.GregorianCalendar();
	
	
	public static String getFormatedDateAfterNHour(int n) {
		gc.add(Calendar.HOUR, +n);
		String DateFin = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format( gc.getTime());
		return DateFin;
	}
	
	
	public static Date getDateAfterNHour(int n) {
		gc.add(Calendar.HOUR, +n);
		return gc.getTime();
	}
}
