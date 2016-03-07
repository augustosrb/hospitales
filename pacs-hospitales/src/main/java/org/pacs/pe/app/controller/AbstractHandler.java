package org.pacs.pe.app.controller;

import org.pacs.pe.util.JsonMessageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractHandler {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	public static JsonMessageResult mensaje = new JsonMessageResult();

	public static JsonMessageResult resultSuccessList(Object objeto) {
		mensaje.setEstado("success");
		mensaje.setMensaje("Registro satisfactorio");
		mensaje.setObjeto(objeto);
		return mensaje;
	}

	public static JsonMessageResult resultSuccessInsert(Object objeto) {
		mensaje.setEstado("success");
		mensaje.setMensaje("Registro satisfactorio");
		mensaje.setObjeto(objeto);
		return mensaje;
	}

	public static JsonMessageResult resultSuccessUpdate(Object objeto) {
		mensaje.setEstado("success");
		mensaje.setMensaje("Actualización satisfactorio");
		mensaje.setObjeto(objeto);
		return mensaje;
	}

	public static JsonMessageResult resultSuccessDelete(Object objeto) {
		mensaje.setEstado("success");
		mensaje.setMensaje("Eliminación satisfactoria");
		mensaje.setObjeto(objeto);
		return mensaje;
	}

	public static JsonMessageResult resultFail(String error) {
		mensaje.setEstado("error");
		mensaje.setMensaje("Registro Fallido : " + error);
		return mensaje;
	}

	public static JsonMessageResult resultAlertUnique(Object result,String campo,Object objeto) { 
		mensaje.setEstado("warning");
		mensaje.setObjeto(result);
		mensaje.setMensaje("Registro existente cambiar el campo " + campo + " : " + objeto);
		return mensaje;
	}
}
