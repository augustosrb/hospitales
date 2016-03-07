package org.pacs.pe.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.pacs.pe.app.dao.MenuDao;
import org.pacs.pe.app.mapper.MenuRowMapper;
import org.pacs.pe.app.model.Menu;
import org.pacs.pe.util.Constantes;
import org.pacs.pe.util.HelperJDBC;
import org.pacs.pe.util.HelperString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class MenuDaoImpl implements MenuDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Menu> findAllMenus(Menu menu) {
			Object[] args = new Object[] {};


			List<Menu> lstMenuPadres = new ArrayList<Menu>();
			/*List<Menu> lstMenuHijos = new ArrayList<Menu>();
			List<Menu> lstMenus = new ArrayList<Menu>();
			List<Menu> lstTempMenus = new ArrayList<Menu>();
			*/
			StringBuilder sqlMenuPadre = new StringBuilder("SELECT "
					+ " M.cod_menu, S.cod_sistema,S.nombre nomSistema, M.nombre, M.descripcion, M.ruta, M.grupo, M.orden, "
					+ " M.estado, M.fecha_creacion, M.fecha_modificacion, M.usuario_creacion, "
					+ " M.usuario_modificacion"
					+ " FROM Menu M inner join Sistema S on m.cod_sistema = s.cod_sistema ");
			sqlMenuPadre.append(" where M.fecha_creacion between ? and ? ");
			
			args  = HelperJDBC.addBetweenDateIni(args, menu.getFechaFiltroIni());
			args  = HelperJDBC.addBetweenDateFin(args, menu.getFechaFiltroFin());
			args  = HelperJDBC.addParameterEquals(sqlMenuPadre, "M", "estado", args, menu.getEstado());
			args  = HelperJDBC.addParameterLike(sqlMenuPadre, "M", "nombre", args, "%"+ menu.getNombre()+"%");
			args  = HelperJDBC.addParameterLike(sqlMenuPadre, "M", "descripcion", args, "%"+ menu.getDescripcion()+"%");
			//args  = HelperJDBC.addParameterEquals(sqlMenuPadre, "M", "orden	", args, Constantes.ORDEN_MENU);
			lstMenuPadres = jdbcTemplate.query(sqlMenuPadre.toString(),args,new MenuRowMapper());
			/*
			StringBuilder sqlMenuHijo = new StringBuilder("SELECT "
					+ " M.cod_menu, S.cod_sistema,S.nombre nomSistema, M.nombre, M.descripcion, M.ruta, M.grupo, M.orden, "
					+ " M.estado, M.fecha_creacion, M.fecha_modificacion, M.usuario_creacion, "
					+ " M.usuario_modificacion"
					+ " FROM Menu M inner join Sistema S on m.cod_sistema = s.cod_sistema ");
			sqlMenuHijo.append(" where M.fecha_creacion between ? and ? ");
			
			args = new Object[] {};
			args  = HelperJDBC.addBetweenDateIni(args, menu.getFechaFiltroIni());
			args  = HelperJDBC.addBetweenDateFin(args, menu.getFechaFiltroFin());
			args  = HelperJDBC.addParameterEquals(sqlMenuHijo, "M", "estado", args, menu.getEstado());
			args  = HelperJDBC.addParameterLike(sqlMenuHijo, "M", "nombre", args, "%"+ menu.getNombre()+"%");
			args  = HelperJDBC.addParameterLike(sqlMenuHijo, "M", "descripcion", args, "%"+ menu.getDescripcion()+"%");
			args  = HelperJDBC.addParameterDiferent(sqlMenuHijo, "M", "orden	", args, Constantes.ORDEN_MENU);
			
			lstMenuHijos = jdbcTemplate.query(sqlMenuHijo.toString(),args,new MenuRowMapper());
			

			for(Menu mPadre : lstMenuPadres)
			{
				for(Menu mHijo : lstMenuHijos)
				{
					if(mHijo.getGrupo().equals(mPadre.getGrupo()))
					{
						lstTempMenus.add(mHijo);						
						//mPadre.setListMenu(new ArrayList<Menu>());;
						//mPadre.getListMenu().add(mHijo);
					}
				}
				mPadre.getListMenu().addAll(lstTempMenus);
				lstMenus.add(mPadre);
				lstTempMenus.clear();
			}
			
			return lstMenus;*/
			
			return lstMenuPadres;
	}
	
	@Override
	public Menu getbyID(Integer cod_menu) {
		
		String sql = "select M.cod_menu, S.cod_sistema,S.nombre nomSistema, M.nombre, M.descripcion, M.ruta, M.grupo, M.orden, M.estado, "
				+ " M.fecha_creacion, M.fecha_modificacion, M.usuario_creacion, M.usuario_modificacion "
				+ " from Menu M"
				+ " inner join Sistema S on M.cod_sistema = s.cod_sistema"
				+ " where M.cod_menu = ?";
		return (Menu)  jdbcTemplate.queryForObject(sql, new Object[]{cod_menu}, new MenuRowMapper());  
	}
	
	@Override
	public List<Menu> listarMenuPadres(Menu menu) {
		
		Object[] args = new Object[] {};
		StringBuilder sql = new StringBuilder("SELECT "
				+ " M.cod_menu, S.cod_sistema,S.nombre nomSistema, M.nombre, M.descripcion, M.ruta, M.grupo, M.orden, "
				+ " M.estado, M.fecha_creacion, M.fecha_modificacion, M.usuario_creacion, "
				+ " M.usuario_modificacion"
				+ " FROM Menu M inner join Sistema S on m.cod_sistema = s.cod_sistema");
		sql.append(" where M.estado = ? ");
		args  = HelperJDBC.appendValue(args, Constantes.ESTADO_ACTIVO);
		args  = HelperJDBC.addParameterEquals(sql, "M", "orden", args, Constantes.ORDEN_MENU);
		
		return jdbcTemplate.query(sql.toString(),args,new MenuRowMapper());
		
	}
	
	@Override
	public Menu saveMenuPadre(final Menu menu) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator()  {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                            connection.prepareStatement("INSERT INTO Menu "
                            		+ "(cod_sistema, nombre, descripcion, ruta, orden, "
                            		+ " estado, fecha_creacion, usuario_creacion)"
                            		+ " VALUES (?,?, ?, ?, ?, ?, ?, ?)", 
                            		new String[] {"cod_menu"});
                        ps.setInt(1, menu.getSistema().getCod_sistema());
                        ps.setString(2, menu.getNombre());
        				ps.setString(3, menu.getDescripcion());
        				ps.setString(4, menu.getRuta());
        				ps.setInt(5, Constantes.ORDEN_MENU);
        				ps.setInt(6, menu.getEstado());
        				ps.setTimestamp(7, HelperString.formatDateToInsert());
        				ps.setString(8, menu.getUsuario_creacion());
                        return ps;
                    }
                },
                keyHolder);
		
		String sql = "update Menu "
				+ "set grupo = ? "
				+ "where cod_menu = ?";
		
		Object[] params = new Object[] {keyHolder.getKey().intValue(), keyHolder.getKey().intValue()};
		
		jdbcTemplate.update(sql, params);
		
		return getbyID(keyHolder.getKey().intValue());
		
	}
	
	@Override
	public Menu saveMenuHijo(final Menu menu) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator()  {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                            connection.prepareStatement("INSERT INTO Menu "
                            		+ "(cod_sistema,nombre, descripcion, ruta, grupo, orden, "
                            		+ " estado, fecha_creacion, usuario_creacion)"
                            		+ " VALUES (?,?, ?, ?, ?, ?, ?, ?, ?)", 
                            		new String[] {"cod_menu"});
                        ps.setInt(1, menu.getSistema().getCod_sistema());
                        ps.setString(2, menu.getNombre());
        				ps.setString(3, menu.getDescripcion());
        				ps.setString(4, menu.getRuta());
        				ps.setInt(5, menu.getGrupo());
        				ps.setInt(6, countOrden(menu));
        				ps.setInt(7, menu.getEstado());
        				ps.setTimestamp(8, HelperString.formatDateToInsert());
        				ps.setString(9, menu.getUsuario_creacion());
                        return ps;
                    }
                },
                keyHolder);
		
        return getbyID(keyHolder.getKey().intValue());
	}

	@Override
	public Menu updateMenuPadre(Menu menu) {
		String sql = "update Menu "
				+ "set estado = ?, "
				+ "nombre = ?, "
				+ "descripcion = ?, "
				+ "cod_sistema = ? "
				+ "where cod_menu = ?";

		Object[] params = new Object[] {menu.getEstado(),menu.getNombre(),
				menu.getDescripcion(),menu.getSistema().getCod_sistema(), menu.getCod_menu()};
		
		jdbcTemplate.update(sql, params);
		
		return getbyID(menu.getCod_menu());
	}
	
	@Override
	public Menu updateMenuHijo(Menu menu) {
		String sql = "update Menu "
				+ "set nombre = ?, "
				+ "descripcion = ?, "
				+ "ruta = ?, "
				+ "grupo = ?, "
				+ "cod_sistema = ?, "
				+ "estado = ? " 
				+ "where cod_menu = ?";
		
		Object[] params = new Object[] {menu.getEstado(),
				menu.getDescripcion(), menu.getRuta(),menu.getCod_menu()};
		
		jdbcTemplate.update(sql, params);
		
		return getbyID(menu.getCod_menu());
	}
	
	@Override
	public void deleteMenu(Integer cod_menu) throws Exception {

		String sql = "update Menu set estado = ?, fecha_modificacion = ? where cod_menu = ?";
		Object[] params = new Object[] { Constantes.ESTADO_INACTIVO, new Date(), cod_menu };

		jdbcTemplate.update(sql, params);

	}
	
	public Integer countOrden(Menu menu)
	{	
		String sql = "select count(orden) from Menu where grupo = ?";
		
		Integer count = jdbcTemplate.queryForObject(
                sql, new Object[] { menu.getGrupo()}, Integer.class);

		return count.intValue();
	}

	@Override
	public Boolean isMenuNombreUnique(Menu menu) {
Object[] args = new Object[] {};
		
		Boolean result = false;
		
		StringBuilder sql = new StringBuilder("select count(1) from Menu M ");
		
		if(!menu.existsnullAtributes(menu))
		{
			
			args = HelperJDBC.addParameterWhereLike(sql, "M", "nombre", args,menu.getNombre().toUpperCase());
			args = HelperJDBC.addParameterDiferent(sql, "M", "cod_menu", args, menu.getCod_menu());
		}
		
		Integer count = jdbcTemplate.queryForObject(
	                        sql.toString(), args, Integer.class);
				
		if (count > 0) {
			result = true;
		}

		return result;	
		
	}
}
