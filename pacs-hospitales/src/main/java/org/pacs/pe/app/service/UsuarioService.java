package org.pacs.pe.app.service;

import java.util.List;

import org.pacs.pe.app.model.Perfil;
import org.pacs.pe.app.model.Rol;
import org.pacs.pe.app.model.Usuario;

public interface UsuarioService {
	
	public List<Usuario> findAllUsuarios(Usuario usuario) throws Exception;
	
	public Usuario getbyID(Integer cod_usuario) throws Exception; 
	
	public Usuario saveUsuario(final Usuario usuario) throws Exception;
	
	public Usuario updateUsuario(Usuario usuario) throws Exception;
	
	public void deleteUsuario(Integer cod_usuario) throws Exception;
	
	public Usuario findUsuarioByNombre(String nombre)  throws Exception;
	
	public Boolean isUsuarioNombreUnique(Usuario usuario);
	
	
	
	public Usuario getLoginUsuario(Usuario usuario);
	
	public void registrarAsocPerfil(Integer[] perfilsInt, Integer cod_usuario);

	public void registrarAsocRol(Integer[] rolsInt, Integer cod_usuario);	
	
	public List<Perfil> getPerfilUsuario(Usuario usuario);

	public List<Rol> getRolUsuario(Usuario usuario);

}
