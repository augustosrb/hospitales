package org.pacs.pe.app.service;

import java.util.List;

import org.pacs.pe.app.model.Menu;

public interface MenuService {
	
	public List<Menu> findAllMenus(Menu menu) throws Exception;
	
	public List<Menu> listarMenuPadres(Menu menu)throws Exception;
	
	public Menu getbyID(Integer cod_menu);
	
	public void deleteMenu(Integer cod_menu) throws Exception;
		
	public Boolean isMenuNombreUnique(Menu menu);

	public Menu saveMenuPadre(Menu menu);

	public Menu updateMenuPadre(Menu menu);

	public Menu saveMenuHijo(Menu menu);

	public Menu updateMenuHijo(Menu menu);



}
