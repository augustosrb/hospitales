package org.pacs.pe.app.dao.impl;

import java.util.List;

import org.pacs.pe.app.dao.ListaEstudiosDao;
import org.pacs.pe.app.model.Estudio;
import org.pacs.pe.util.Constantes;
import org.pacs.pe.util.HelperJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public class ListaEstudiosDaoImpl implements ListaEstudiosDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Estudio> findAllEtudios(Estudio estudio) {
		
		Object[] args = new Object[] {};
		StringBuilder sql = new StringBuilder(
				"select E.cod_estudio,E.pat_id, E.pat_name, E.modality, E.estado, E.diagnostico, E.fecha_registro,"
				+ "E.fecha_diagnostico, E.study_iuid, E.usuario_modificacion" + " from Estudio E ");
		sql.append(" where E.fecha_registro between ? and ? ");
		args = HelperJDBC.addBetweenDateIni(args, estudio.getFechaRegistroIni());
		args = HelperJDBC.addBetweenDateFin(args, estudio.getFechaRegistroFin());
		
		if(!estudio.existsnullAtributes(estudio))
		{
			args = HelperJDBC.addParameterLike(sql, "E", "pat_id", args, "%" + estudio.getPat_id()+ "%");
			args = HelperJDBC.addParameterLike(sql, "E", "pat_name", args, "%" + estudio.getPat_name() + "%");
			args = HelperJDBC.addParameterLike(sql, "E", "modality", args, "%" + estudio.getModality() + "%");
		}
		return jdbcTemplate.query(sql.toString(), args, new BeanPropertyRowMapper<Estudio>(Estudio.class));
	}
	
	
	

}
