package org.pacs.pe.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.pacs.pe.app.model.Rol;
import org.pacs.pe.app.model.Sistema;
import org.springframework.jdbc.core.RowMapper;

public class RolRowMapper implements RowMapper<Rol>  {

	@Override
	public Rol mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Sistema sistema = new Sistema();
		sistema.setCod_sistema(rs.getInt("cod_sistema"));
		sistema.setNombre(rs.getString("nomSistema"));
		Rol rol = new Rol();
		rol.setCod_rol(rs.getInt("cod_rol"));
		rol.setSistema(sistema);
		rol.setNombre(rs.getString("nombre"));
		rol.setDescripcion(rs.getString("descripcion"));
		rol.setEstado(rs.getInt("estado"));
		rol.setFecha_creacion(rs.getDate("fecha_creacion"));
		rol.setFecha_modificacion(rs.getDate("fecha_modificacion"));
		rol.setUsuario_creacion(rs.getString("usuario_creacion"));
		rol.setUsuario_modificacion(rs.getString("usuario_modificacion"));
		
		return rol;
	}

}
