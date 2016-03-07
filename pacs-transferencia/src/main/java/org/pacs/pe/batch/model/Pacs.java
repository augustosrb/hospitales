package org.pacs.pe.batch.model;

import java.util.Date;

public class Pacs {
	private String study_id;
	private Date created_time;
	private Date update_time;
	private String study_desc;
	private String pat_id;
	private String pat_name;
	private String pat_sex;
	private String pat_birthdate;
	private String institution;
	private String modality;
	private String src_aet;
	private String cod_clinica;
	private Integer estado;
	private String study_iuid;
	private Date fecha_registro;
	private String pat_correo;

	public String getStudy_id() {
		return this.study_id;
	}

	public void setStudy_id(String study_id) {
		this.study_id = study_id;
	}

	public Date getCreated_time() {
		return this.created_time;
	}

	public void setCreted_date(Date creted_date) {
		this.created_time = creted_date;
	}

	public Date getUpdate_time() {
		return this.update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public String getStudy_desc() {
		return this.study_desc;
	}

	public void setStudy_desc(String study_desc) {
		this.study_desc = study_desc;
	}

	public String getPat_id() {
		return this.pat_id;
	}

	public void setPat_id(String pat_id) {
		this.pat_id = pat_id;
	}

	public String getPat_name() {
		return this.pat_name;
	}

	public void setPat_name(String pat_name) {
		this.pat_name = pat_name;
	}

	public String getPat_sex() {
		return this.pat_sex;
	}

	public void setPat_sex(String pat_sex) {
		this.pat_sex = pat_sex;
	}

	public String getPat_birthdate() {
		return this.pat_birthdate;
	}

	public void setPat_birthdate(String pat_birthdate) {
		this.pat_birthdate = pat_birthdate;
	}

	public String getInstitution() {
		return this.institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getModality() {
		return this.modality;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	public String getSrc_aet() {
		return this.src_aet;
	}

	public void setSrc_aet(String src_aet) {
		this.src_aet = src_aet;
	}

	public String getStudy_iuid() {
		return this.study_iuid;
	}

	public void setStudy_iuid(String study_iuid) {
		this.study_iuid = study_iuid;
	}

	public Date getFecha_registro() {
		return this.fecha_registro;
	}

	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public String getPat_correo() {
		return this.pat_correo;
	}

	public void setPat_correo(String pat_correo) {
		this.pat_correo = pat_correo;
	}

	public String getCod_clinica() {
		return this.cod_clinica;
	}

	public void setCod_clinica(String cod_clinica) {
		this.cod_clinica = cod_clinica;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String toString() {
		return

		"Pacs [study_id=" + this.study_id + ", creted_date=" + this.created_time + ", update_time=" + this.update_time
				+ ", study_desc=" + this.study_desc + ", pat_id=" + this.pat_id + ", pat_name=" + this.pat_name
				+ ", pat_sex=" + this.pat_sex + ", pat_birthdate=" + this.pat_birthdate + ", institution="
				+ this.institution + ", modality=" + this.modality + ", src_aet=" + this.src_aet + ", study_iuid="
				+ this.study_iuid + ", fecha_registro=" + this.fecha_registro + "]";
	}
}
