package org.pacs.pe.app.dao;

import java.util.List;

import org.pacs.pe.app.model.Perfil;
import org.pacs.pe.app.model.Rol;
import org.pacs.pe.app.model.Usuario;

public interface UsuarioDao {

	public List<Usuario> findAllUsuarios(Usuario usuario) throws Exception;

	public Usuario getbyID(Integer cod_usuario)throws Exception;
	
	public Usuario saveUsuario(final Usuario usuario) throws Exception;

	public Usuario updateUsuario(Usuario usuario) throws Exception;

	public void deleteUsuario(Integer cod_usuario) throws Exception;

	public Usuario findUsuarioByNombre(String nombre);

	public Boolean isUsuarioNombreUnique(Usuario usuario);
	
	public Usuario getLoginUsuario(Usuario usuario);
	
	public void registrarAsocPerfil(Integer[] perfilsInt, Integer cod_usuario);

	public void registrarAsocRol(Integer[] rolsInt, Integer cod_usuario);
	
	public List<Rol> getRolUsuario(Usuario usuario);

	public List<Perfil> getPerfilUsuario(Usuario usuario);
}
