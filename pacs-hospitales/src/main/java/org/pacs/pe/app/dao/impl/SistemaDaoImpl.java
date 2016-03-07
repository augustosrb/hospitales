package org.pacs.pe.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.pacs.pe.app.dao.SistemaDao;
import org.pacs.pe.app.model.Sistema;
import org.pacs.pe.util.Constantes;
import org.pacs.pe.util.HelperJDBC;
import org.pacs.pe.util.HelperString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class SistemaDaoImpl implements SistemaDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Sistema> findAllSistemas(Sistema sistema) throws Exception {

		Object[] args = new Object[] {};
		StringBuilder sql = new StringBuilder(
				"select S.cod_sistema, S.nombre, S.descripcion, S.estado, S.fecha_creacion, S.fecha_modificacion,"
						+ " S.usuario_creacion, S.usuario_modificacion" + " from Sistema S ");
		
		if(!sistema.existsnullAtributes(sistema))
		{
			sql.append(" where S.fecha_creacion between ? and ? ");
			args = HelperJDBC.addBetweenDateIni(args, sistema.getFechaFiltroIni());
			args = HelperJDBC.addBetweenDateFin(args, sistema.getFechaFiltroFin());
			args = HelperJDBC.addParameterEquals(sql, "S", "estado", args, sistema.getEstado());
			args = HelperJDBC.addParameterLike(sql, "S", "nombre", args, "%" + sistema.getNombre() + "%");
			args = HelperJDBC.addParameterLike(sql, "S", "descripcion", args, "%" + sistema.getDescripcion() + "%");
		}


		return jdbcTemplate.query(sql.toString(), args, new BeanPropertyRowMapper<Sistema>(Sistema.class));
	}

	@Override
	public Sistema getbyID(Integer cod_sistema) {
		String sql = "select * from Sistema where cod_sistema = ?";
		return (Sistema) jdbcTemplate.queryForObject(sql, new Object[] { cod_sistema },
				new BeanPropertyRowMapper<Sistema>(Sistema.class));
	}

	@Override
	public Sistema saveSistema(final Sistema sistema) throws Exception {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"INSERT INTO Sistema" + " (nombre, descripcion, estado," + " fecha_creacion, "
								+ " usuario_creacion, usuario_modificacion)" + " VALUES (?, ?, ?, ?, ?, ?)",
						new String[] { "cod_sistema" });
				ps.setString(1, sistema.getNombre().toUpperCase());
				ps.setString(2, sistema.getDescripcion());
				ps.setInt(3, sistema.getEstado());
				ps.setTimestamp(4, HelperString.formatDateToInsert());
				ps.setString(5, sistema.getUsuario_creacion());
				ps.setString(6, sistema.getUsuario_modificacion());
				return ps;
			}
		}, keyHolder);

		System.out.println("id: " + keyHolder.getKey().intValue());

		return getbyID(keyHolder.getKey().intValue());
	}

	@Override
	public Sistema updateSistema(Sistema sistema) throws Exception {
		String sql = "UPDATE Sistema" + " SET nombre=?, descripcion=?, estado=?,"
				+ " fecha_modificacion=?, usuario_modificacion=?" + " WHERE cod_sistema = ?;";

		Object[] params = new Object[] { sistema.getNombre().toUpperCase(), sistema.getDescripcion(), sistema.getEstado(), new Date(),
				sistema.getUsuario_modificacion(), sistema.getCod_sistema() };

		jdbcTemplate.update(sql, params);

		return getbyID(sistema.getCod_sistema());
	}

	@Override
	public void deleteSistema(Integer cod_sistema) throws Exception {

		String sql = "update Sistema set estado = ?, fecha_modificacion = ? where cod_sistema = ?;";
		Object[] params = new Object[] { Constantes.ESTADO_INACTIVO, new Date(), cod_sistema };

		jdbcTemplate.update(sql, params);

	}
	
	@Override
	public Sistema findSistemaByNombre(String nombre) 
	{
		String sql = "select * from Sistema where nombre = ?";
		
		try {
			return (Sistema) jdbcTemplate.queryForObject(sql, new Object[] { nombre },
					new BeanPropertyRowMapper<Sistema>(Sistema.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}	 
	}

	@Override
	public Boolean isSistemaNombreUnique(Sistema sistema) {
		Object[] args = new Object[] {};
		
		Boolean result = false;
		
		StringBuilder sql = new StringBuilder("select count(1) from Sistema S ");
	
		args = HelperJDBC.addParameterWhereLike(sql, "S", "nombre", args,sistema.getNombre().toUpperCase());
		if(!sistema.existsnullAtributes(sistema))
		{
			
			args = HelperJDBC.addParameterDiferent(sql, "S", "cod_sistema", args, sistema.getCod_sistema());
		}
		
		Integer count = jdbcTemplate.queryForObject(
	                        sql.toString(), args, Integer.class);
				
		if (count > 0) {
			result = true;
		}

		return result;	  
	}

}
