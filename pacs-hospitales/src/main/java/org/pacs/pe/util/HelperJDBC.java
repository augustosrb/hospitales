package org.pacs.pe.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class HelperJDBC {

	public static Object[] addBetweenDateIni(Object[] obj, Object newObj) {
		if (newObj != null && !newObj.equals("")) {
			return appendValue(obj, HelperString.formatFecIniFiltro(newObj.toString()));
		}
		else
		{
			return appendValue(obj,HelperString.obtenerPrimerDiaMes());
		}
	}
	
	public static Object[] addBetweenDateFin(Object[] obj, Object newObj) {
		if (newObj != null && !newObj.equals("")) {
			return appendValue(obj, HelperString.formatFecFinFiltro(newObj.toString()));
		}
		else
		{
			return appendValue(obj,HelperString.obtenerUltimoDiaMes());
		}
	}
	
	public static Object[] addParameterEquals(StringBuilder sql, String alias, String parameter, Object[] obj, Object newObj) {
		if (newObj != null && !newObj.equals("")) {
			sql.append(" and  " + alias + "." + parameter+" = ? ");
			return appendValue(obj, newObj);
		}
		return obj;
	}
	
	public static Object[] addParameterDiferent(StringBuilder sql, String alias, String parameter, Object[] obj, Object newObj) {
		if (newObj != null && !newObj.equals("")) {
			sql.append(" and  " + alias + "." + parameter+" != ? ");
			return appendValue(obj, newObj);
		}
		return obj;
	}
	
	public static Object[] addParameterLike(StringBuilder sql, String alias, String parameter, Object[] obj, Object newObj) {
		if (newObj != null && !newObj.equals("")) {
			sql.append(" and " + alias + "." + parameter + " LIKE ? ");
			return appendValue(obj, newObj);
		}
		return obj;
	}
	
	
	public static Object[] addParameterWhereLike(StringBuilder sql, String alias, String parameter, Object[] obj, Object newObj) {
		if (newObj != null && !newObj.equals("")) {
			sql.append(" where " + alias + "." + parameter + " LIKE ? ");
			return appendValue(obj, newObj);
		}
		return obj;
	}

	
	public static Object[] appendValue(Object[] obj, Object newObj) {

		ArrayList<Object> temp = new ArrayList<Object>(Arrays.asList(obj));
		temp.add(newObj);
		return temp.toArray();

	}
	
	public static Object[] appendValueByIndex(Object[] obj, Integer index,Object newObj) {

		ArrayList<Object> temp = new ArrayList<Object>(Arrays.asList(obj));
		temp.set(index, newObj);
		return temp.toArray();

	}
}
