package org.pacs.pe.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.pacs.pe.app.model.Sistema;
import org.pacs.pe.app.model.Usuario;
import org.springframework.jdbc.core.RowMapper;

public class UsuarioRowMapper implements RowMapper<Usuario> {
	
	@Override
	public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
		Usuario usuario = new Usuario();
		Sistema sistema = new Sistema();
		sistema.setCod_sistema(rs.getInt("cod_sistema"));
		sistema.setNombre(rs.getString("nomSistema"));
		usuario.setCod_usuario(rs.getInt("cod_usuario"));
		usuario.setSistema(sistema);
		usuario.setNombre(rs.getString("nombre"));
		usuario.setApepat(rs.getString("apepat"));
		usuario.setApemat(rs.getString("apemat"));
		usuario.setCorreo(rs.getString("correo"));
		usuario.setLogin(rs.getString("login"));
		usuario.setPassword(rs.getString("password"));
		usuario.setEstado(rs.getInt("estado"));
		usuario.setFecha_creacion(rs.getDate("fecha_creacion"));
		usuario.setFecha_modificacion(rs.getDate("fecha_modificacion"));
		usuario.setUsuario_creacion(rs.getString("usuario_creacion"));
		usuario.setUsuario_modificacion(rs.getString("usuario_modificacion"));
	
		
		return usuario;
	}
}
