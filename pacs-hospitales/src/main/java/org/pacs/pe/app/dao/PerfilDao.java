package org.pacs.pe.app.dao;

import java.util.List;

import org.pacs.pe.app.model.Menu;
import org.pacs.pe.app.model.Perfil;

public interface PerfilDao {

	public List<Perfil> findAllPerfiles(Perfil perfil) throws Exception;

	public Perfil getbyID(Integer cod_perfil)throws Exception;
	
	public Perfil savePerfil(final Perfil perfil) throws Exception;

	public Perfil updatePerfil(Perfil perfil) throws Exception;

	public void deletePerfil(Integer cod_perfil) throws Exception;

	public Perfil findPerfilByNombre(String nombre);

	public Boolean isPerfilNombreUnique(Perfil perfil);
	
	public List<Menu> getMenuPerfil(Perfil cod_perfil);
	
	public void registrarAsocMenu(Integer[] menusInt, Integer cod_perfil);

	public Integer[] getPerfilesUsuario(Integer cod_usuario);
}
