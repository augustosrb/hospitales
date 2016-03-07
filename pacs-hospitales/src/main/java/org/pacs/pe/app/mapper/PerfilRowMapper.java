package org.pacs.pe.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.pacs.pe.app.model.Perfil;
import org.pacs.pe.app.model.Sistema;
import org.springframework.jdbc.core.RowMapper;

public class PerfilRowMapper implements RowMapper<Perfil>  {

	@Override
	public Perfil mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Sistema sistema = new Sistema();
		sistema.setCod_sistema(rs.getInt("cod_sistema"));
		sistema.setNombre(rs.getString("nomSistema"));
		Perfil perfil = new Perfil();
		perfil.setCod_perfil(rs.getInt("cod_perfil"));
		perfil.setSistema(sistema);
		perfil.setNombre(rs.getString("nombre"));
		perfil.setDescripcion(rs.getString("descripcion"));
		perfil.setEstado(rs.getInt("estado"));
		perfil.setFecha_creacion(rs.getDate("fecha_creacion"));
		perfil.setFecha_modificacion(rs.getDate("fecha_modificacion"));
		perfil.setUsuario_creacion(rs.getString("usuario_creacion"));
		perfil.setUsuario_modificacion(rs.getString("usuario_modificacion"));
		
		return perfil;
	}
}
