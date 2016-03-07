package org.pacs.pe.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.pacs.pe.app.dao.UsuarioDao;
import org.pacs.pe.app.mapper.UsuarioRowMapper;
import org.pacs.pe.app.model.Perfil;
import org.pacs.pe.app.model.Rol;
import org.pacs.pe.app.model.Usuario;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDaoImpl implements UsuarioDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Usuario> findAllUsuarios(Usuario usuario) throws Exception {

		Object[] args = new Object[] {};
		StringBuilder sql = new StringBuilder("SELECT "
				+ " U.cod_usuario, S.cod_sistema,S.nombre nomSistema, U.nombre, U.apepat, U.apemat, U.correo, U.login, "
				+ " U.password, U.estado, U.fecha_creacion, U.fecha_modificacion, U.usuario_creacion,"
				+ " U.usuario_modificacion, U.usuario_registrado, U.especialista_registrado "
				+ " FROM Usuario U inner join Sistema S on u.cod_sistema = s.cod_sistema");
		sql.append(" where U.fecha_creacion between ? and ? ");
		
		args  = HelperJDBC.addBetweenDateIni(args, usuario.getFechaFiltroIni());
		args  = HelperJDBC.addBetweenDateFin(args, usuario.getFechaFiltroFin());
		args  = HelperJDBC.addParameterEquals(sql, "U", "estado", args, usuario.getEstado());
		args  = HelperJDBC.addParameterLike(sql, "U", "nombre", args, "%"+ usuario.getNombre()+"%");
		args  = HelperJDBC.addParameterLike(sql, "U", "apepat", args, "%"+ usuario.getApepat()+"%");
		args  = HelperJDBC.addParameterLike(sql, "U", "apemat", args, "%"+ usuario.getApemat()+"%");
		args  = HelperJDBC.addParameterLike(sql, "U", "correo", args, "%"+ usuario.getCorreo()+"%");
		
		return jdbcTemplate.query(sql.toString(),args,new UsuarioRowMapper());
	}

	@Override
	public Usuario getbyID(Integer cod_usuario) {
		String sql = "SELECT "
				+ " U.cod_usuario, S.cod_sistema,S.nombre nomSistema, U.nombre, U.apepat, U.apemat, U.correo, U.login, "
				+ " U.password, U.estado, U.fecha_creacion, U.fecha_modificacion, U.usuario_creacion,"
				+ " U.usuario_modificacion, U.usuario_registrado, U.especialista_registrado "
				+ " FROM Usuario U inner join Sistema S on u.cod_sistema = s.cod_sistema"
				+ " where cod_usuario = ?";
		return (Usuario)  jdbcTemplate.queryForObject(sql, new Object[]{cod_usuario}, new UsuarioRowMapper());  
	}

	@Override
	public Usuario saveUsuario(final Usuario usuario) throws Exception {

		final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator()  {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                            connection.prepareStatement("INSERT INTO usuario"
												+ "(cod_sistema,nombre, apepat, apemat, correo, login, "
												+ " password, estado, fecha_creacion, usuario_creacion)"
												+ " VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?)", 
												new String[] {"cod_usuario"});
                        ps.setInt(1, usuario.getSistema().getCod_sistema());
                        ps.setString(2, usuario.getNombre().toUpperCase());
        				ps.setString(3, usuario.getApepat().toUpperCase());
        				ps.setString(4, usuario.getApemat().toUpperCase());
        				ps.setString(5, usuario.getCorreo());
        				ps.setString(6, usuario.getLogin());
        				ps.setString(7, encoder.encode(Constantes.PASSWORD));
        				ps.setInt(8, usuario.getEstado());
        				ps.setTimestamp(9, HelperString.formatDateToInsert());
        				ps.setString(10, usuario.getUsuario_creacion());
                        return ps;
                    }
                },
                keyHolder);
		
        return getbyID(keyHolder.getKey().intValue());
	}

	@Override
	public Usuario updateUsuario(Usuario usuario) throws Exception {
		String sql = "UPDATE Usuario"
				+ " SET nombre=?, apepat=?, apemat=?, correo=?,estado=?,cod_sistema=?,"
				+ " fecha_modificacion=?, usuario_modificacion=?"
				+ " WHERE cod_usuario = ?;";
		
		Object[] params = new Object[] {usuario.getNombre().toUpperCase(),
				usuario.getApepat().toUpperCase(), usuario.getApemat().toUpperCase(),
				usuario.getCorreo(),usuario.getEstado(),usuario.getSistema().getCod_sistema(),
				new Date(), usuario.getUsuario_modificacion(),usuario.getCod_usuario()};
		
		jdbcTemplate.update(sql, params);
		
		return getbyID(usuario.getCod_usuario());
	}

	@Override
	public void deleteUsuario(Integer cod_usuario) throws Exception {

		String sql = "update Usuario set estado = ?, fecha_modificacion = ? where cod_usuario = ?;";
		Object[] params = new Object[] { Constantes.ESTADO_INACTIVO,new Date(),cod_usuario};
		
		jdbcTemplate.update(sql, params);

	}
	
	@Override
	public Usuario findUsuarioByNombre(String nombre) 
	{
		String sql = "select * from Usuario where nombre = ?";
		
		try {
			return (Usuario) jdbcTemplate.queryForObject(sql, new Object[] { nombre },
					new BeanPropertyRowMapper<Usuario>(Usuario.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}	 
	}

	@Override
	public Boolean isUsuarioNombreUnique(Usuario usuario) {
		Object[] args = new Object[] {};
		
		Boolean result = false;
		
		StringBuilder sql = new StringBuilder("select count(1) from Usuario U ");
		
		if(!usuario.existsnullAtributes(usuario))
		{
			
			args = HelperJDBC.addParameterWhereLike(sql, "U", "nombre", args,usuario.getNombre().toUpperCase());
			args = HelperJDBC.addParameterDiferent(sql, "U", "cod_sistema", args, usuario.getCod_usuario());
		}
		
		Integer count = jdbcTemplate.queryForObject(
	                        sql.toString(), args, Integer.class);
				
		if (count > 0) {
			result = true;
		}

		return result;	  	 
	}
	
	@Override
	public Usuario getLoginUsuario(Usuario usuario) {
		String sql = "select count(*) from Usuario where login = ?";
		
		Integer count = jdbcTemplate.queryForObject(
                sql, new Object[] { usuario.getNombre().toLowerCase() + "." +usuario.getApepat().toLowerCase()}, Integer.class);

		usuario.setLogin(count != 0 
				? usuario.getNombre().toLowerCase() + "." + usuario.getApepat().toLowerCase() + (count+1) 
				: usuario.getNombre().toLowerCase() + "." + usuario.getApepat().toLowerCase()); 
		
		return usuario;
	}
	
	@Override
	public void registrarAsocPerfil(Integer[] perfilsInt, Integer cod_usuario) {
		deletePerfilUsuario(cod_usuario);

		if(perfilsInt[0] != 0){
			Object[] args = new Object[2];
			String query = "INSERT INTO perfil_usuario(cod_usuario,cod_perfil) VALUES (?, ?)";
			args = HelperJDBC.appendValueByIndex(args, 0, cod_usuario);
			for(Integer cod_perfil : perfilsInt)         
			{
				args = HelperJDBC.appendValueByIndex(args, 1, cod_perfil);
		        jdbcTemplate.update(query, args);			
			}
		}
		
	}

	@Override
	public void registrarAsocRol(Integer[] rolsInt, Integer cod_usuario) {
		deleteRolUsuario(cod_usuario);
		if(rolsInt[0] != 0){
			Object[] args = new Object[2];
			String query = "INSERT INTO rol_usuario(cod_usuario,cod_rol) VALUES (?, ?)";
			
			args = HelperJDBC.appendValueByIndex(args, 0, cod_usuario);
			for(Integer cod_rol : rolsInt)         
			{
				args = HelperJDBC.appendValueByIndex(args, 1, cod_rol);
				jdbcTemplate.update(query, args);			
			}
		}
	}
	
	public void deleteRolUsuario(Integer cod_usuario) {
		Object[] args = new Object[] {};
		StringBuilder query = new StringBuilder("delete from rol_usuario where cod_usuario = ?");
		args = HelperJDBC.appendValue(args, cod_usuario);
		jdbcTemplate.update(query.toString(), args);		
	}
	
	public void deletePerfilUsuario(Integer cod_usuario) {
		Object[] args = new Object[] {};
		StringBuilder query = new StringBuilder("delete from perfil_usuario where cod_usuario = ?");
		args = HelperJDBC.appendValue(args, cod_usuario);
		jdbcTemplate.update(query.toString(), args);		
	}
	
	@Override
	public List<Rol> getRolUsuario(Usuario usuario) {
		Object[] args = new Object[] {};
		StringBuilder sql = new StringBuilder("select r.cod_rol,r.cod_sistema,r.nombre,ru.cod_rol rolAsoc from rol r"
				+ " left join rol_usuario ru"
				+ " on r.cod_rol = ru.cod_rol and ru.cod_usuario = ? "
				+ " where r.estado = ?");
		
		args = HelperJDBC.appendValue(args, usuario.getCod_usuario());
		args = HelperJDBC.appendValue(args, Constantes.ESTADO_ACTIVO);
		//args = HelperJDBC.appendValue(args, usuario.getSistema().getCod_sistema());
		
		return jdbcTemplate.query(sql.toString(),args,new BeanPropertyRowMapper<Rol>(Rol.class));		

	}

	@Override
	public List<Perfil> getPerfilUsuario(Usuario usuario) {
		Object[] args = new Object[] {};
		StringBuilder sql = new StringBuilder("select p.cod_perfil,p.cod_sistema,p.nombre,pu.cod_perfil perfilAsoc from perfil p"
				+ " left join perfil_usuario pu"
				+ " on p.cod_perfil = pu.cod_perfil and pu.cod_usuario = ? "
				+ " where p.estado = ? ");

		args = HelperJDBC.appendValue(args, usuario.getCod_usuario());
		args = HelperJDBC.appendValue(args, Constantes.ESTADO_ACTIVO);
		
		return jdbcTemplate.query(sql.toString(),args,new BeanPropertyRowMapper<Perfil>(Perfil.class));
	}
	
	
}
