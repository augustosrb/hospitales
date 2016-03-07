package org.pacs.pe.app.service.impl;

import java.util.List;

import org.pacs.pe.app.dao.LoginDao;
import org.pacs.pe.app.model.Menu;
import org.pacs.pe.app.model.Rol;
import org.pacs.pe.app.model.Usuario;
import org.pacs.pe.app.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	
	
	@Autowired
	private LoginDao loginDao;
	
	@Override
	@Transactional(readOnly = true)
	public Usuario getUsuarioLogin(String usuario) {
		return loginDao.getUsuarioLogin( usuario);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Rol> getRolUsuario(Integer cod_usuario) {
		return loginDao.getRolUsuario(cod_usuario);
	}

	
	@Override
	@Transactional(readOnly = true)
	public Integer[] getPerfilesUsuario(Integer cod_usuario) {
		return loginDao.getPerfilesUsuario(cod_usuario);
	}
	

	@Override
	@Transactional(readOnly = true)
	public List<Menu> listMenusCompleta(Menu menu, Integer[] perfiles) {
		return loginDao.listMenusCompleta(menu,perfiles);
	}
}
