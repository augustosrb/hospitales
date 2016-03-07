package org.pacs.pe.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

import org.pacs.pe.util.Constantes;

public class Estudio {
	private Integer cod_estudio;

	private String pat_id;
	private String pat_name;
	private String modality;
	private String pat_birthdate;
	private String institution;
	private Integer estado;
	private String descEstado;
	private Integer newEstado;
	private String descNewEstado;
	private String diagnostico;
	private Date fecha_registro;
	private Date fecha_diagnostico;
	private String study_iuid;

	private String usuario_creacion;
	private String usuario_modificacion;

	private String fechaRegistroIni;
	private String fechaRegistroFin;

	public Integer getCod_estudio() {
		return cod_estudio;
	}

	public void setCod_estudio(Integer cod_estudio) {
		this.cod_estudio = cod_estudio;
	}

	public String getPat_id() {
		return pat_id;
	}

	public void setPat_id(String pat_id) {
		this.pat_id = pat_id;
	}

	public String getPat_name() {
		return pat_name;
	}

	public void setPat_name(String pat_name) {
		this.pat_name = pat_name;
	}

	public String getModality() {
		return modality;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	public Integer getEstado() {
		return estado;
	}

	public String getPat_birthdate() {
		return pat_birthdate;
	}

	public void setPat_birthdate(String pat_birthdate) {
		this.pat_birthdate = pat_birthdate;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getDescEstado() {
		return Constantes.obtenerEstadoEstudio(estado);
	}

	public void setDescEstado(String descEstado) {
		this.descEstado = descEstado;
	}

	public Integer getNewEstado() {
		return newEstado;
	}

	public void setNewEstado(Integer newEstado) {
		this.newEstado = newEstado;
	}

	public String getDescNewEstado() {
		return descNewEstado;
	}

	public void setDescNewEstado(String descNewEstado) {
		this.descNewEstado = descNewEstado;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getFecha_diagnostico() {
		return fecha_diagnostico;
	}

	public void setFecha_diagnostico(Date fecha_diagnostico) {
		this.fecha_diagnostico = fecha_diagnostico;
	}

	public String getStudy_iuid() {
		return study_iuid;
	}

	public void setStudy_iuid(String study_iuid) {
		this.study_iuid = study_iuid;
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

	public String getFechaRegistroIni() {
		return fechaRegistroIni;
	}

	public void setFechaRegistroIni(String fechaRegistroIni) {
		this.fechaRegistroIni = fechaRegistroIni;
	}

	public String getFechaRegistroFin() {
		return fechaRegistroFin;
	}

	public void setFechaRegistroFin(String fechaRegistroFin) {
		this.fechaRegistroFin = fechaRegistroFin;
	}

	public boolean existsnullAtributes(Object obj) {
		if (pat_id != null) {
			return false;
		}
		if (pat_name != null) {
			return false;
		}
		if (modality != null) {
			return false;
		}
		if (estado != null) {
			return false;
		}
		return true;
	}
}
