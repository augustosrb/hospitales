package org.pacs.pe.app.dao.impl;

import java.util.Date;
import java.util.List;

import org.pacs.pe.app.dao.ListaTrabajoDao;
import org.pacs.pe.app.model.Estudio;
import org.pacs.pe.app.security.User;
import org.pacs.pe.util.Constantes;
import org.pacs.pe.util.HelperJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ListaTrabajoDaoImpl implements ListaTrabajoDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Estudio> findAllTrabajos(Estudio estudio) {

		Object[] args = new Object[] {};
		StringBuilder sql = new StringBuilder(
				"select E.cod_estudio,E.pat_id, E.pat_name, E.modality, E.estado, E.diagnostico, E.fecha_registro,"
						+ "E.fecha_diagnostico, E.study_iuid, E.usuario_modificacion" + " from Estudio E ");
		sql.append(" where E.fecha_registro between ? and ?  and estado = ?");
		args = HelperJDBC.addBetweenDateIni(args, estudio.getFechaRegistroIni());
		args = HelperJDBC.addBetweenDateFin(args, estudio.getFechaRegistroFin());
		args = HelperJDBC.appendValue(args, Constantes.ESTADO_RECIBIDO);
		
		if (!estudio.existsnullAtributes(estudio)) {
			args = HelperJDBC.addParameterLike(sql, "E", "pat_id", args, "%" + estudio.getPat_id() + "%");
			args = HelperJDBC.addParameterLike(sql, "E", "pat_name", args, "%" + estudio.getPat_name() + "%");
			args = HelperJDBC.addParameterLike(sql, "E", "modality", args, "%" + estudio.getModality() + "%");
		}
		return jdbcTemplate.query(sql.toString(), args, new BeanPropertyRowMapper<Estudio>(Estudio.class));
	}

	@Override
	public Estudio diagnosticar(Estudio estudio) {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String sql = "update Estudio set estado = ?, fecha_diagnostico = ?, "
				+ " usuario_modificacion= ?,diagnostico = ? where cod_estudio = ?;";
		Object[] params = new Object[] { Constantes.ESTADO_DIAGNOSTICADO, new Date(), user.getUsername(), estudio.getDiagnostico(),
				estudio.getCod_estudio() };

		jdbcTemplate.update(sql, params);

		return getEstudiobyID(estudio.getCod_estudio());
	}

	@Override
	public Estudio getEstudiobyID(Integer cod_estudio) {
		Object[] args = new Object[] {};
		StringBuilder sql = new StringBuilder(
				"select E.cod_estudio,E.pat_id, E.pat_name, E.modality, E.estado, E.diagnostico, E.fecha_registro,"
						+ "E.fecha_diagnostico, E.study_iuid, E.usuario_modificacion" + " from Estudio E ");
		sql.append(" where E.cod_estudio = ? ");
		args = HelperJDBC.appendValue(args, cod_estudio);

		return jdbcTemplate.queryForObject(sql.toString(), args, new BeanPropertyRowMapper<Estudio>(Estudio.class));
	}

	@Override
	public Estudio bloqueoEstudio(Integer cod_estudio) {
		String sql = "update Estudio set estado = ?, " + " usuario_modificacion= ? where cod_estudio = ?;";
		Object[] params = new Object[] { Constantes.ESTADO_BLOQUEADO, "Hola", cod_estudio };

		jdbcTemplate.update(sql, params);
		
		return getEstudiobyID(cod_estudio);

	}

	@Override
	public Estudio desbloqueoEstudio(Estudio estudio) {
		String sql = "update Estudio set estado = ?, " + " usuario_modificacion= ? where cod_estudio = ?;";
		Object[] params = new Object[] { Constantes.ESTADO_RECIBIDO, "Hola", estudio.getCod_estudio() };

		jdbcTemplate.update(sql, params);

		return getEstudiobyID(estudio.getCod_estudio());
	}

}
