package org.pacs.pe.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer cod_usuario;

	private String nombre;
	private String apemat;
	private String apepat;
	private String correo;
	private String login;
	private String password;
	private Integer estado;
	private Date fecha_creacion;
	private Date fecha_modificacion;
	private String usuario_creacion;
	private String usuario_modificacion;
	private Integer usuario_registrado;
	private Integer especialista_registrado;
	private Integer trabajador_registrado;
	private Integer paciente_registrado;
	
	private Sistema sistema;
	private String fechaFiltroIni;
	private String fechaFiltroFin;

	public Usuario() {
	}

	public Integer getCod_usuario() {
		return cod_usuario;
	}

	public void setCod_usuario(Integer cod_usuario) {
		this.cod_usuario = cod_usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApepat() {
		return this.apepat;
	}

	public void setApepat(String apepat) {
		this.apepat = apepat;
	}

	public String getApemat() {
		return this.apemat;
	}

	public void setApemat(String apemat) {
		this.apemat = apemat;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

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

	public Integer getUsuario_registrado() {
		return usuario_registrado;
	}

	public void setUsuario_registrado(Integer usuario_registrado) {
		this.usuario_registrado = usuario_registrado;
	}

	public Integer getEspecialista_registrado() {
		return especialista_registrado;
	}

	public void setEspecialista_registrado(Integer especialista_registrado) {
		this.especialista_registrado = especialista_registrado;
	}

	public Integer getTrabajador_registrado() {
		return trabajador_registrado;
	}

	public void setTrabajador_registrado(Integer trabajador_registrado) {
		this.trabajador_registrado = trabajador_registrado;
	}
	
	public Integer getPaciente_registrado() {
		return paciente_registrado;
	}

	public void setPaciente_registrado(Integer paciente_registrado) {
		this.paciente_registrado = paciente_registrado;
	}

	public Sistema getSistema() {
		return this.sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
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
	
	public boolean existsnullAtributes(Object obj) {
		if (cod_usuario != null) {
				return false;
		}
		if (nombre!= null) {
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