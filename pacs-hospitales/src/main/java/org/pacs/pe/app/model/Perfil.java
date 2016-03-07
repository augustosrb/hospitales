package org.pacs.pe.app.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Perfil implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer cod_perfil;
	private String descripcion;
	private String nombre;
	private Integer estado;
	private Date fecha_creacion;
	private Date fecha_modificacion;
	private String usuario_creacion;
	private String usuario_modificacion;
	
	private String fechaFiltroIni;
	private String fechaFiltroFin;

	private Sistema sistema;
	private Object perfilAsoc;

	public Integer getCod_perfil() {
		return cod_perfil;
	}

	public void setCod_perfil(Integer cod_perfil) {
		this.cod_perfil = cod_perfil;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public Date getFecha_modificacion() {
		return fecha_modificacion;
	}

	public void setFecha_modificacion(Date fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}

	public String getUsuario_creacion() {
		return usuario_creacion;
	}

	public void setUsuario_creacion(String usuario_creacion) {
		this.usuario_creacion = usuario_creacion;
	}

	public String getUsuario_modificacion() {
		return usuario_modificacion;
	}

	public void setUsuario_modificacion(String usuario_modificacion) {
		this.usuario_modificacion = usuario_modificacion;
	}

	public String getFechaFiltroIni() {
		return fechaFiltroIni;
	}

	public void setFechaFiltroIni(String fechaFiltroIni) {
		this.fechaFiltroIni = fechaFiltroIni;
	}

	public String getFechaFiltroFin() {
		return fechaFiltroFin;
	}

	public void setFechaFiltroFin(String fechaFiltroFin) {
		this.fechaFiltroFin = fechaFiltroFin;
	}

	public Object getPerfilAsoc() {
		return perfilAsoc;
	}

	public void setPerfilAsoc(Object perfilAsoc) {
		this.perfilAsoc = perfilAsoc;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}
	
	public boolean existsnullAtributes(Object obj) {
		if (cod_perfil!= null) {
				return false;
		}
		if (nombre != null) {
				return false;
		} 	
		if (estado != null) {
				return false;
		}	
		if (fechaFiltroFin != null) {
				return false;
		}
		if (fechaFiltroIni != null) {
				return false;
		}
		if (fecha_creacion != null) {
				return false;
		}
		if (fecha_modificacion != null) {
				return false;
		}
		if (nombre != null) {
				return false;
		}
		if (usuario_creacion != null) {
				return false;
		}if (usuario_modificacion != null) {
				return false;
		}
		return true;
	}
}
