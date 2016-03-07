package org.pacs.pe.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HelperString {

	static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	// JDBC
	public static Date formatFecIniFiltro(String fechIni) {

		Date fechaIni = null;
		try {
			fechaIni = formatter.parse(fechIni);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fechaIni;
	}

	public static Date formatFecFinFiltro(String fechFin) {

		Date fechaFin = null;

		try {
			fechaFin = formatter.parse(fechFin);
			Calendar newDate = Calendar.getInstance();
			newDate.setTime(fechaFin);
			newDate.add(Calendar.DATE, 1);
			newDate.add(Calendar.MILLISECOND, -1000);
			fechaFin = newDate.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  fechaFin;
	}

	public static Date obtenerPrimerDiaMes() {

		Date date = new Date();
		try {
			Calendar newDate = Calendar.getInstance();
			newDate.setTime(date);
			newDate.set(Calendar.DAY_OF_MONTH, 1);
			date = newDate.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(date);
		return date;
	}

	public static Date obtenerUltimoDiaMes() {

		Date date = new Date();
		try {
			Calendar newDate = Calendar.getInstance();
			newDate.setTime(date);
			newDate.add(Calendar.MONTH, 1);
			newDate.set(Calendar.DAY_OF_MONTH, 1);
			newDate.add(Calendar.DATE, -1);
			date = newDate.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Timestamp formatDateToInsert() {
		
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MILLISECOND, 0);
		
		return new java.sql.Timestamp(date.getTime());
	}
	

	// JDBC
		
	public static boolean containsChar(String s, char search) {
	    if (s.length() == 0)
	        return false;
	    else
	        return s.charAt(0) == search || containsChar(s.substring(1), search);
	}
}
