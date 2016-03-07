package org.pacs.pe.batch.dao;

import java.util.Date;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.pacs.pe.batch.model.Pacs;
import org.pacs.pe.batch.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class PacsDaoImpl implements PacsDao {
	static Logger logger = Logger.getLogger(PacsDaoImpl.class);
	@Autowired
	private DataSource dataSourcePostgreSql;
	@Autowired
	private DataSource dataSource;

	public DataSource getDataSourcePostgreSql() {
		return this.dataSourcePostgreSql;
	}

	public void setDataSourcePostgreSql(DataSource dataSourcePostgreSql) {
		this.dataSourcePostgreSql = dataSourcePostgreSql;
	}

	public DataSource getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void saveWorkStudyPacs(Pacs pacs) {
		if (logger.isDebugEnabled()) {
			logger.debug("start method saveWorkStudyPacs(Pacs pacs)");
		}
		String query = "insert into temp_study (study_iuid,fecha_registro) values (?,?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
		Object[] args = { pacs.getStudy_iuid(), pacs.getFecha_registro() };

		int out = jdbcTemplate.update(query, args);
		if (logger.isInfoEnabled()) {
			logger.info("Resultado del registro: " + out);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Resultado del registro: " + out);
		}
	}

	public void saveWorkStudyInfast(Pacs pacs) {
		if (logger.isDebugEnabled()) {
			logger.debug("start method saveWorkStudyInfast(Pacs pacs)");
		}
		String query = "INSERT INTO tempworkstudy( cod_centro_asistencial, study_id, created_time,  updated_time, study_desc, pat_id, pat_name, pat_sex, pat_birthdate, modality, institution, src_aet, study_iuid, estado, fecha_registro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSourcePostgreSql);
		Object[] args = { pacs.getCod_clinica(), pacs.getStudy_id(), pacs.getCreated_time(), pacs.getUpdate_time(),
				pacs.getStudy_desc(), pacs.getPat_id(), pacs.getPat_name(), pacs.getPat_sex(), pacs.getPat_birthdate(),
				pacs.getModality(), pacs.getInstitution(), pacs.getSrc_aet(), pacs.getStudy_iuid(),
				Constantes.ESTADO_ACTIVO, new Date() };

		int out = jdbcTemplate.update(query, args);
		if (logger.isInfoEnabled()) {
			logger.info("Resultado del registro: " + out);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Resultado del registro: " + out);
		}
	}
}
