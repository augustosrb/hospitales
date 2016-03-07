package org.pacs.pe.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.pacs.pe.app.dao.PerfilDao;
import org.pacs.pe.app.mapper.PerfilRowMapper;
import org.pacs.pe.app.model.Menu;
import org.pacs.pe.app.model.Perfil;
import org.pacs.pe.util.Constantes;
import org.pacs.pe.util.HelperJDBC;
import org.pacs.pe.util.HelperString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class PerfilDaoImpl implements PerfilDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Perfil> findAllPerfiles(Perfil perfil) throws Exception {

		Object[] args = new Object[] {};
		StringBuilder sql = new StringBuilder("select "
				+ " R.cod_perfil, S.cod_sistema,S.nombre nomSistema, R.nombre, R.descripcion, R.estado, R.fecha_creacion, R.fecha_modificacion,"
				+ " R.usuario_creacion, R.usuario_modificacion"
				+ " from Perfil R inner join Sistema S on r.cod_sistema = s.cod_sistema");
		sql.append(" where R.fecha_creacion between ? and ? ");
		
		
		args  = HelperJDBC.addBetweenDateIni(args, perfil.getFechaFiltroIni());
		args  = HelperJDBC.addBetweenDateFin(args, perfil.getFechaFiltroFin());
		args  = HelperJDBC.addParameterEquals(sql, "R", "estado", args, perfil.getEstado());
		args  = HelperJDBC.addParameterLike(sql, "R", "nombre", args, "%"+ perfil.getNombre()+"%");
		args  = HelperJDBC.addParameterLike(sql, "R", "descripcion", args, "%"+ perfil.getDescripcion()+"%");

		return jdbcTemplate.query(sql.toString(), args, new PerfilRowMapper());
	}

	@Override
	public Perfil getbyID(Integer cod_perfil) {
		String sql = "select "
				+ " R.cod_perfil, S.cod_sistema,S.nombre nomSistema, R.nombre, R.descripcion, R.estado, R.fecha_creacion, R.fecha_modificacion,"
				+ " R.usuario_creacion, R.usuario_modificacion"
				+ " from Perfil R inner join Sistema S on r.cod_sistema = s.cod_sistema"
				+ " where cod_perfil = ?";
		return (Perfil) jdbcTemplate.queryForObject(sql, new Object[] { cod_perfil },	new PerfilRowMapper());
	}

	@Override
	public Perfil savePerfil(final Perfil perfil) throws Exception {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"INSERT INTO Perfil"
        				+ " (cod_sistema,nombre, descripcion, estado,"
        				+ " fecha_creacion, "
        				+ " usuario_creacion, usuario_modificacion)"
        				+ " VALUES (?,?, ?, ?, ?, ?, ?)",
						new String[] { "cod_perfil" });
				ps.setInt(1, perfil.getSistema().getCod_sistema());
                ps.setString(2, perfil.getNombre().toUpperCase());
 				ps.setString(3, perfil.getDescripcion());
 				ps.setInt(4, perfil.getEstado());
 				ps.setTimestamp(5, HelperString.formatDateToInsert());
 				ps.setString(6, perfil.getUsuario_creacion());
 				ps.setString(7, perfil.getUsuario_modificacion());
				return ps;
			}
		}, keyHolder);

		return getbyID(keyHolder.getKey().intValue());
	}

	@Override
	public Perfil updatePerfil(Perfil perfil) throws Exception {
		String sql = "UPDATE Perfil"
				+ " SET nombre=?, descripcion=?, estado=?, cod_sistema=?,"
				+ " fecha_modificacion=?, usuario_modificacion=?"
				+ " WHERE cod_perfil = ?";

		Object[] params = new Object[] { perfil.getNombre().toUpperCase(), 
				perfil.getDescripcion(), perfil.getEstado(), perfil.getSistema().getCod_sistema(),new Date(),
				perfil.getUsuario_modificacion(), perfil.getCod_perfil() };

		jdbcTemplate.update(sql, params);

		return getbyID(perfil.getCod_perfil());
	}

	@Override
	public void deletePerfil(Integer cod_perfil) throws Exception {

		String sql = "update Perfil set estado = ?, fecha_modificacion = ? where cod_perfil = ?";
		Object[] params = new Object[] { Constantes.ESTADO_INACTIVO, new Date(), cod_perfil };

		jdbcTemplate.update(sql, params);

	}
	
	@Override
	public Perfil findPerfilByNombre(String nombre) 
	{
		String sql = "select * from Perfil where nombre = ?";
		
		try {
			return (Perfil) jdbcTemplate.queryForObject(sql, new Object[] { nombre },
					new BeanPropertyRowMapper<Perfil>(Perfil.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}	 
	}

	@Override
	public Boolean isPerfilNombreUnique(Perfil perfil) {
		Object[] args = new Object[] {};
		
		Boolean result = false;
		
		StringBuilder sql = new StringBuilder("select count(1) from Perfil P ");
		
		if(!perfil.existsnullAtributes(perfil))
		{
			
			args = HelperJDBC.addParameterWhereLike(sql, "P", "nombre", args,perfil.getNombre().toUpperCase());
			args = HelperJDBC.addParameterDiferent(sql, "P", "cod_perfil", args, perfil.getCod_perfil());
		}
		
		Integer count = jdbcTemplate.queryForObject(
	                        sql.toString(), args, Integer.class);
				
		if (count > 0) {
			result = true;
		}

		return result;	 
	}
	
	@Override
	public void registrarAsocMenu(Integer[] menusInt, Integer cod_perfil) {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		
		deleteMenuPerfil(cod_perfil);
		List menuList = Arrays.asList(menusInt);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("menus", menuList );
		
		StringBuilder sql = new StringBuilder("select distinct m.grupo from Menu m "
				+ " where m.cod_menu in (:menus) ");
		
		List<Integer> lstMenu = namedParameterJdbcTemplate.queryForList(sql.toString(), parameters, Integer.class);
					
		Object[] args1 = new Object[2];
		String query1 = "INSERT INTO menu_perfil(cod_perfil,cod_menu) VALUES (?, ?)";
		
		args1 = HelperJDBC.appendValueByIndex(args1, 0, cod_perfil);
		
		for(Integer cod_menu : lstMenu)
		{	
				args1 = HelperJDBC.appendValueByIndex(args1, 1, cod_menu);
				jdbcTemplate.update(query1, args1);			
		}

		if(menusInt[0] != 0){
			Object[] args = new Object[2];
			String query = "INSERT INTO menu_perfil(cod_perfil,cod_menu) VALUES (?, ?)";
			args = HelperJDBC.appendValueByIndex(args, 0, cod_perfil);
			for(Integer cod_menu : menusInt)         
			{
				args = HelperJDBC.appendValueByIndex(args, 1, cod_menu);
		        jdbcTemplate.update(query, args);			
			}
		}
	}
	
	public void deleteMenuPerfil(Integer cod_perfil) {
		Object[] args = new Object[] {};
		StringBuilder query = new StringBuilder("delete from menu_perfil where cod_perfil = ?");
		args = HelperJDBC.appendValue(args, cod_perfil);
		jdbcTemplate.update(query.toString(), args);		
	}
	
	@Override
	public Integer[] getPerfilesUsuario(Integer cod_usuario) {
		Object[] args = new Object[] {};
		
		StringBuilder sql = new StringBuilder(
				"select p.cod_perfil"
						+ " FROM Perfil P  inner join perfilxusuario PU "
						+ "  on pu.cod_perfil = p.cod_perfil and PU.cod_usuario =  ?");

		args = HelperJDBC.appendValue(args, cod_usuario);
		
		List<Perfil>  lstPerfil = jdbcTemplate.query(sql.toString(),args,new BeanPropertyRowMapper<Perfil>(Perfil.class));	
		
		Integer contador = 0;
		Integer [] intArray = new Integer[lstPerfil.size()];
		for(Perfil p : lstPerfil)
		{
			intArray[contador] = p.getCod_perfil();
			
			contador++;
		}
		
		return intArray;
	}
	
	@Override
	public List<Menu> getMenuPerfil(Perfil perfil) {
		Object[] args = new Object[] {};
		StringBuilder sql = new StringBuilder("select m.cod_menu,m.nombre,pm.cod_menu menuAsoc,m.orden,"
				+ " (select nombre from menu where cod_menu = m.grupo) as menuPadreNom from menu m "
				+ " left join menu_perfil pm "
				+ " on m.cod_menu = pm.cod_menu and pm.cod_perfil = ? "
				+ " where m.estado = ? order by orden");
		
		args = HelperJDBC.appendValue(args, perfil.getCod_perfil());
		args = HelperJDBC.appendValue(args, Constantes.ESTADO_ACTIVO);
		
		return jdbcTemplate.query(sql.toString(),args,new BeanPropertyRowMapper<Menu>(Menu.class));	
	}
}
