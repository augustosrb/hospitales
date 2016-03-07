package org.pacs.pe.app.service;

import java.util.List;

import org.pacs.pe.app.model.Menu;
import org.pacs.pe.app.model.Rol;
import org.pacs.pe.app.model.Usuario;

public interface LoginService {
	
	public Usuario getUsuarioLogin(String usuario);
	
	public List<Rol> getRolUsuario(Integer cod_usuario);
	
	public Integer[] getPerfilesUsuario(Integer cod_usuario);
	
	public List<Menu> listMenusCompleta(Menu menu, Integer[] perfiles);
}
