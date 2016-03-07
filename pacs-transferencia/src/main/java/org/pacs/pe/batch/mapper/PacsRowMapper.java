package org.pacs.pe.batch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.pacs.pe.batch.model.Pacs;
import org.springframework.jdbc.core.RowMapper;

public class PacsRowMapper implements RowMapper<Pacs> {
	static Logger logger = Logger.getLogger(PacsRowMapper.class);

	public Pacs mapRow(ResultSet rs, int arg1) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("start method mapRow(ResultSet rs, int arg1)");
		}
		Logger logger = Logger.getLogger(PacsRowMapper.class);

		logger.debug("Pacs Row Mapper INI");
		Pacs pacs = new Pacs();

		pacs.setCod_clinica(rs.getString("study_custom3") != null ? rs.getString("study_custom3") : "");
		pacs.setStudy_id(rs.getString("study_id") != null ? rs.getString("study_id") : "");
		pacs.setCreted_date(rs.getDate("created_time"));
		pacs.setUpdate_time(rs.getDate("updated_time"));
		pacs.setStudy_desc(rs.getString("study_desc") != null ? rs.getString("study_desc") : "");
		pacs.setPat_id(rs.getString("pat_id") != null ? rs.getString("pat_id") : "");
		pacs.setPat_name(rs.getString("pat_name") != null ? rs.getString("pat_name") : "");
		pacs.setPat_sex(rs.getString("pat_sex") != null ? rs.getString("pat_sex") : "");
		pacs.setPat_birthdate(rs.getString("pat_birthdate") != null ? rs.getString("pat_birthdate") : "");
		pacs.setInstitution(rs.getString("institution") != null ? rs.getString("institution") : "");
		pacs.setModality(rs.getString("modality") != null ? rs.getString("modality") : "");
		pacs.setSrc_aet(rs.getString("src_aet") != null ? rs.getString("src_aet") : "");
		pacs.setStudy_iuid(rs.getString("study_iuid") != null ? rs.getString("study_iuid") : "");
		if (logger.isDebugEnabled()) {
			logger.debug(pacs.toString());
		}
		return pacs;
	}
}
