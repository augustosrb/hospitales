package org.pacs.pe.util;

import java.util.Calendar;
import java.util.Date;

public class Constantes {

	public static final String PASSWORD = "123456";

	public static final Integer ESTADO_ACTIVO = 0;
	public static final Integer ESTADO_INACTIVO = 1;
	
	public static final Integer ESTADO_RECIBIDO = 0;
	public static final Integer ESTADO_DIAGNOSTICADO = 1;
	public static final Integer ESTADO_INFORMADO = 2;
	public static final Integer ESTADO_BLOQUEADO = 3;
	
	public static final  String DESCESTADO_BLOQUEADO = "Bloqueado";
	

	public static final Integer ORDEN_MENU = 0;
	
	public static final String ESTADO_UNIQUE_TRUE = "UNIQUE";
	public static final String ESTADO_UNIQUE_FALSE = "NOUNIQUE";
	
	
	public static String obtenerEstadoEstudio(Integer estado) {
		String res="";
		if(estado==0)
		{
			res = "Recibido"; 
		}
		if(estado==1)
		{
			res = "Diagnosticado"; 
		}
		if(estado==2)
		{
			res = "Informado"; 
		}
		if(estado==3)
		{
			res = "Bloqueado"; 
		}
		return res;
	}
	
}
