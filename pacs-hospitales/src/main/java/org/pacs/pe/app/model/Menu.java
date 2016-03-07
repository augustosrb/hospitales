package org.pacs.pe.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Menu implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer cod_menu;
	private String nombre;
	private String descripcion;
	private Integer estado;
	private Date fecha_creacion;
	private Date fecha_modificacion;
	private Integer grupo;
	private Integer orden;
	private String ruta;
	private String usuario_creacion;
	private String usuario_modificacion;

	private String fechaFiltroIni;
	private String fechaFiltroFin;
	
	private Sistema sistema;
	
	private List<Menu> listMenu = new ArrayList<Menu>();
	
	private Object menuAsoc;
	private Object menuPadreNom;

	public Integer getCod_menu() {
		return cod_menu;
	}

	public void setCod_menu(Integer cod_menu) {
		this.cod_menu = cod_menu;
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

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	public Date getFecha_modificacion() {
		return this.fecha_modificacion;
	}

	public void setFecha_modificacion(Date fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}

	public Integer getGrupo() {
		return grupo;
	}

	public void setGrupo(Integer grupo) {
		this.grupo = grupo;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
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

	public Object getMenuAsoc() {
		return menuAsoc;
	}

	public void setMenuAsoc(Object menuAsoc) {
		this.menuAsoc = menuAsoc;
	}

	public Object getMenuPadreNom() {
		return menuPadreNom;
	}

	public void setMenuPadreNom(Object menuPadreNom) {
		this.menuPadreNom = menuPadreNom;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public List<Menu> getListMenu() {
		return listMenu;
	}

	public void setListMenu(List<Menu> listMenu) {
		this.listMenu = listMenu;
	}
	
	public boolean existsnullAtributes(Object obj) {
		if (cod_menu!= null) {
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
