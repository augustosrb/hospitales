package org.pacs.pe.app.controller;

import org.pacs.pe.app.model.Menu;
import org.pacs.pe.app.model.Perfil;
import org.pacs.pe.app.service.MenuService;
import org.pacs.pe.util.JsonMessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pacs")
public class MenuController extends AbstractHandler {

	/*@Autowired
	private PerfilService perfilService;*/
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value = "/mntMenus")
	public String mntMenus() {
		return "mntMenus";
	}

	@RequestMapping(value = "/listarMenus")
	@ResponseBody
	public JsonMessageResult listarMenus(Menu menu) {
		try {
			return resultSuccessList(menuService.findAllMenus(menu));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/listarMenuPadres")
	@ResponseBody
	public JsonMessageResult listarMenuPadres(Menu menu) {
		try {
			return resultSuccessList(menuService.listarMenuPadres(menu));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/buscaMenu")
	@ResponseBody
	public JsonMessageResult buscaMenu(Integer cod_menu) {
		try {
			return resultSuccessList(menuService.getbyID(cod_menu));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}

	@RequestMapping(value = "/saveMenuPadre")
	public @ResponseBody JsonMessageResult saveMenuPadre(Menu menu) {
		try {
			return resultSuccessInsert(menuService.saveMenuPadre(menu));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updateMenuPadre")
	public @ResponseBody JsonMessageResult updateMenuPadre(Menu menu) {
		try {
			return resultSuccessUpdate(menuService.updateMenuPadre(menu));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/saveMenuHijo")
	public @ResponseBody JsonMessageResult saveMenuHijo(Menu menu) {
		try {
			return resultSuccessInsert(menuService.saveMenuHijo(menu));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/updateMenuHijo")
	public @ResponseBody JsonMessageResult updateMenuHijo(Menu menu) {
		try {
			return resultSuccessUpdate(menuService.updateMenuHijo(menu));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/deleteMenu")
	@ResponseBody
	public JsonMessageResult deleteMenu(Integer cod_menu) {
		try {
			menuService.deleteMenu(cod_menu);
			return resultSuccessDelete(cod_menu);
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}

	@RequestMapping(value = "/isMenuNombreUnique")
	@ResponseBody
	public JsonMessageResult isMenuNombreUnique(Menu menu) {
		return resultAlertUnique(menuService.isMenuNombreUnique(menu),
					"Nombre",menu.getNombre());
	}
}
