package org.pacs.pe.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.pacs.pe.app.model.Menu;
import org.pacs.pe.app.model.Sistema;
import org.springframework.jdbc.core.RowMapper;

public class MenuRowMapper implements RowMapper<Menu> {
	
	@Override
	public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
		Sistema sistema = new Sistema();
		sistema.setCod_sistema(rs.getInt("cod_sistema"));
		sistema.setNombre(rs.getString("nomSistema"));
		
		Menu menu = new Menu();
		menu.setCod_menu(rs.getInt("cod_menu"));
		menu.setSistema(sistema);
		menu.setNombre(rs.getString("nombre"));
		menu.setDescripcion(rs.getString("descripcion"));
		menu.setRuta(rs.getString("ruta"));
		menu.setGrupo(rs.getInt("grupo"));
		menu.setOrden(rs.getInt("orden"));
		menu.setEstado(rs.getInt("estado"));
		menu.setFecha_creacion(rs.getDate("fecha_creacion"));
		menu.setFecha_modificacion(rs.getDate("fecha_modificacion"));
		menu.setUsuario_creacion(rs.getString("usuario_creacion"));
		menu.setUsuario_modificacion(rs.getString("usuario_modificacion"));
		return menu;
	}

}
