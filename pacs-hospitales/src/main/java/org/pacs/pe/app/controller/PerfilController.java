package org.pacs.pe.app.controller;

import org.pacs.pe.app.model.Perfil;
import org.pacs.pe.app.service.PerfilService;
import org.pacs.pe.util.JsonMessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pacs")
public class PerfilController extends AbstractHandler {

	@Autowired
	private PerfilService perfilService;

	@RequestMapping(value = "/mntPerfiles")
	public String mntPerfiles(Perfil perfil) {
		return "mntPerfiles";
	}

	@RequestMapping(value = "/listarPerfiles")
	@ResponseBody
	public JsonMessageResult listarPerfiles(Perfil perfil) {
		try {
			return resultSuccessList(perfilService.findAllPerfiles(perfil));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}

	@RequestMapping(value = "/savePerfil")
	@ResponseBody
	public JsonMessageResult savePerfil(Perfil perfil) {
		try {
			return resultSuccessInsert(perfilService.savePerfil(perfil));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}

	@RequestMapping(value = "/updatePerfil")
	@ResponseBody
	public JsonMessageResult updatePerfil(Perfil perfil) {
		try {
			return resultSuccessUpdate(perfilService.updatePerfil(perfil));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}

	@RequestMapping(value = "/deletePerfil")
	@ResponseBody
	public JsonMessageResult deletePerfil(Integer cod_perfil) {
		try {
			perfilService.deletePerfil(cod_perfil);
			return resultSuccessDelete(cod_perfil);
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}

	@RequestMapping(value = "/buscaPerfil")
	@ResponseBody
	public JsonMessageResult buscaPerfil(Integer cod_perfil) {
		try {
			return resultSuccessList(perfilService.getbyID(cod_perfil));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}

	@RequestMapping(value = "/isPerfilNombreUnique")
	@ResponseBody
	public JsonMessageResult isPerfilNombreUnique(Perfil perfil) {
		return resultAlertUnique(perfilService.isPerfilNombreUnique(perfil),
					"Nombre",perfil.getNombre());
	}
	
	@RequestMapping(value = "/getMenuPerfil")
	public @ResponseBody JsonMessageResult getMenuPerfil(Perfil perfil) {
		try {
			return resultSuccessList(perfilService.getMenuPerfil(perfil));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}
	
	@RequestMapping(value="/registrarAsocMenu")
	public @ResponseBody JsonMessageResult registrarAsocMenu
	(@RequestParam(value = "menusInt") Integer [] menusInt, @RequestParam(value = "cod_perfil")  Integer cod_perfil){
		
		try {
			perfilService.registrarAsocMenu(menusInt,cod_perfil);
			return resultSuccessInsert(menusInt);
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}
}
