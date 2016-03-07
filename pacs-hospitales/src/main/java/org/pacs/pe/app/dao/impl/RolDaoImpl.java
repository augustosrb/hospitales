package org.pacs.pe.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.pacs.pe.app.dao.RolDao;
import org.pacs.pe.app.mapper.RolRowMapper;
import org.pacs.pe.app.model.Rol;
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
public class RolDaoImpl implements RolDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Rol> findAllRoles(Rol rol) throws Exception {

		Object[] args = new Object[] {};
		StringBuilder sql = new StringBuilder("select "
				+ " R.cod_rol, S.cod_sistema,S.nombre nomSistema, R.nombre, R.descripcion, R.estado, R.fecha_creacion, R.fecha_modificacion,"
				+ " R.usuario_creacion, R.usuario_modificacion"
				+ " from Rol R inner join Sistema S on r.cod_sistema = s.cod_sistema");
		sql.append(" where R.fecha_creacion between ? and ? ");
		
		args  = HelperJDBC.addBetweenDateIni(args, rol.getFechaFiltroIni());
		args  = HelperJDBC.addBetweenDateFin(args, rol.getFechaFiltroFin());
		args  = HelperJDBC.addParameterEquals(sql, "R", "estado", args, rol.getEstado());
		args  = HelperJDBC.addParameterLike(sql, "R", "nombre", args, "%"+ rol.getNombre()+"%");
		args  = HelperJDBC.addParameterLike(sql, "R", "descripcion", args, "%"+ rol.getDescripcion()+"%");

		return jdbcTemplate.query(sql.toString(), args, new RolRowMapper());
	}

	@Override
	public Rol getbyID(Integer cod_rol) {
		String sql = "select "
				+ " R.cod_rol, S.cod_sistema,S.nombre nomSistema, R.nombre, R.descripcion, R.estado, R.fecha_creacion, R.fecha_modificacion,"
				+ " R.usuario_creacion, R.usuario_modificacion"
				+ " from Rol R inner join Sistema S on r.cod_sistema = s.cod_sistema"
				+ " where cod_rol = ?";
		return (Rol) jdbcTemplate.queryForObject(sql, new Object[] { cod_rol },	new RolRowMapper());
	}

	@Override
	public Rol saveRol(final Rol rol) throws Exception {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"INSERT INTO Rol"
        				+ " (cod_sistema,nombre, descripcion, estado,"
        				+ " fecha_creacion, "
        				+ " usuario_creacion, usuario_modificacion)"
        				+ " VALUES (?,?, ?, ?, ?, ?, ?)",
						new String[] { "cod_rol" });
				ps.setInt(1, rol.getSistema().getCod_sistema());
                ps.setString(2, rol.getNombre());
 				ps.setString(3, rol.getDescripcion());
 				ps.setInt(4, rol.getEstado());
 				ps.setTimestamp(5, HelperString.formatDateToInsert());
 				ps.setString(6, rol.getUsuario_creacion());
 				ps.setString(7, rol.getUsuario_modificacion());
				return ps;
			}
		}, keyHolder);

		return getbyID(keyHolder.getKey().intValue());
	}

	@Override
	public Rol updateRol(Rol rol) throws Exception {
		String sql = "UPDATE Rol"
				+ " SET nombre=?, descripcion=?, estado=?, cod_sistema=?,"
				+ " fecha_modificacion=?, usuario_modificacion=?"
				+ " WHERE cod_rol = ?";

		Object[] params = new Object[] { rol.getNombre(), rol.getDescripcion(), rol.getEstado(),
				rol.getSistema().getCod_sistema(), new Date(),
				rol.getUsuario_modificacion(), rol.getCod_rol() };

		jdbcTemplate.update(sql, params);

		return getbyID(rol.getCod_rol());
	}

	@Override
	public void deleteRol(Integer cod_rol) throws Exception {

		String sql = "update Rol set estado = ?, fecha_modificacion = ? where cod_rol = ?";
		Object[] params = new Object[] { Constantes.ESTADO_INACTIVO, new Date(), cod_rol };

		jdbcTemplate.update(sql, params);

	}
	
	@Override
	public Rol findRolByNombre(String nombre) 
	{
		String sql = "select * from Rol where nombre = ?";
		
		try {
			return (Rol) jdbcTemplate.queryForObject(sql, new Object[] { nombre },
					new BeanPropertyRowMapper<Rol>(Rol.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}	 
	}

	@Override
	public Boolean isRolNombreUnique(Rol rol) {
		
		Object[] args = new Object[] {};
		
		Boolean result = false;
		
		StringBuilder sql = new StringBuilder("select count(1) from Rol R ");
		
		if(!rol.existsnullAtributes(rol))
		{
			
			args = HelperJDBC.addParameterWhereLike(sql, "R", "nombre", args,rol.getNombre().toUpperCase());
			args = HelperJDBC.addParameterDiferent(sql, "R", "cod_rol", args, rol.getCod_rol());
		}
		
		Integer count = jdbcTemplate.queryForObject(
	                        sql.toString(), args, Integer.class);
				
		if (count > 0) {
			result = true;
		}

		return result;	 
	}
}
