package org.pacs.pe.app.service;

import java.util.List;

import org.pacs.pe.app.model.Menu;
import org.pacs.pe.app.model.Perfil;

public interface PerfilService {
	
	public List<Perfil> findAllPerfiles(Perfil perfil) throws Exception;
	
	public Perfil getbyID(Integer cod_perfil) throws Exception; 
	
	public Perfil savePerfil(final Perfil perfil) throws Exception;
	
	public Perfil updatePerfil(Perfil perfil) throws Exception;
	
	public void deletePerfil(Integer cod_perfil) throws Exception;
	
	public Perfil findPerfilByNombre(String nombre)  throws Exception;
	
	public Boolean isPerfilNombreUnique(Perfil perfil);
	
	public void registrarAsocMenu(Integer[] menusInt, Integer cod_perfil);

	public Integer[] getPerfilesUsuario(Integer cod_usuario);
	
	public List<Menu> getMenuPerfil(Perfil perfil);

}
