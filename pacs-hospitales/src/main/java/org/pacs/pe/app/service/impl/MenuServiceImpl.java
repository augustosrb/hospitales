package org.pacs.pe.app.service.impl;

import java.util.List;

import org.pacs.pe.app.dao.MenuDao;
import org.pacs.pe.app.model.Menu;
import org.pacs.pe.app.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;

	@Override
	@Transactional(readOnly = true)
	public List<Menu> findAllMenus(Menu menu) throws Exception {
		return menuDao.findAllMenus(menu);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Menu> listarMenuPadres(Menu menu) throws Exception {
		return menuDao.listarMenuPadres(menu);
	}
	

	@Override
	public Menu getbyID(Integer cod_menu) {
		return menuDao.getbyID(cod_menu);
	}
	
	@Override
	@Transactional
	public Menu saveMenuPadre(Menu menu) {
		return menuDao.saveMenuPadre(menu);
	}

	@Override
	@Transactional
	public Menu updateMenuPadre(Menu menu) {
		return menuDao.updateMenuPadre(menu);
	}

	@Override
	@Transactional
	public Menu saveMenuHijo(Menu menu) {
		return menuDao.saveMenuHijo(menu);
	}

	@Override
	@Transactional
	public Menu updateMenuHijo(Menu menu) {
		return menuDao.updateMenuHijo(menu);
	}
	
		@Override
	@Transactional
	public void deleteMenu(Integer cod_menu) throws Exception {
		menuDao.deleteMenu(cod_menu);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Boolean isMenuNombreUnique(Menu menu) {
		return menuDao.isMenuNombreUnique(menu);
	}

}
