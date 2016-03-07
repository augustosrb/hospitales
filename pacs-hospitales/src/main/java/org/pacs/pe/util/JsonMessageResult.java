package org.pacs.pe.util;

import java.io.Serializable;

public class JsonMessageResult implements Serializable {

	private static final long serialVersionUID = -5018083946950204092L;

	private String estado;
	private String mensaje;
	private Object objeto;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Object getObjeto() {
		return objeto;
	}

	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}

}