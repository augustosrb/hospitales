package org.pacs.pe.app.service.impl;

import java.util.List;

import org.pacs.pe.app.dao.PerfilDao;
import org.pacs.pe.app.model.Menu;
import org.pacs.pe.app.model.Perfil;
import org.pacs.pe.app.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PerfilServiceImpl implements PerfilService {

	@Autowired
	private PerfilDao perfilDao;

	@Override
	@Transactional(readOnly = true)
	public List<Perfil> findAllPerfiles(Perfil perfil) throws Exception {
		return perfilDao.findAllPerfiles(perfil);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Perfil getbyID(Integer cod_perfil) throws Exception {
		return perfilDao.getbyID(cod_perfil);
	}

	@Override
	@Transactional
	public Perfil savePerfil(final Perfil perfil) throws Exception {
		return perfilDao.savePerfil(perfil);
	}

	@Override
	@Transactional
	public Perfil updatePerfil(Perfil perfil) throws Exception {
		return perfilDao.updatePerfil(perfil);
	}

	@Override
	@Transactional
	public void deletePerfil(Integer cod_perfil) throws Exception {
		perfilDao.deletePerfil(cod_perfil);
	}
	
	@Override
	@Transactional(readOnly = true)	
	public Perfil findPerfilByNombre(String nombre)
	{
		return perfilDao.findPerfilByNombre(nombre);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Boolean isPerfilNombreUnique(Perfil perfil) {
		return perfilDao.isPerfilNombreUnique(perfil);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Menu> getMenuPerfil(Perfil perfil) {
		return perfilDao.getMenuPerfil(perfil);
	}
	
	@Override
	@Transactional
	public void registrarAsocMenu(Integer[] menusInt, Integer cod_perfil) {
		perfilDao.registrarAsocMenu(menusInt,cod_perfil);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Integer[] getPerfilesUsuario(Integer cod_usuario) {
		return perfilDao.getPerfilesUsuario(cod_usuario);
	}
}
