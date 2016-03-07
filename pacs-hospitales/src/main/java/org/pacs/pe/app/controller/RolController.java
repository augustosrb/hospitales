package org.pacs.pe.app.controller;

import org.pacs.pe.app.model.Rol;
import org.pacs.pe.app.service.RolService;
import org.pacs.pe.util.JsonMessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pacs")
public class RolController extends AbstractHandler {

	@Autowired
	private RolService rolService;

	@RequestMapping(value = "/mntRoles")
	public String mntRoles(Rol rol) {
		return "mntRoles";
	}

	@RequestMapping(value = "/listarRoles")
	@ResponseBody
	public JsonMessageResult listarRoles(Rol rol) {
		try {
			return resultSuccessList(rolService.findAllRoles(rol));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}

	@RequestMapping(value = "/saveRol")
	@ResponseBody
	public JsonMessageResult saveRol(Rol rol) {
		try {
			return resultSuccessInsert(rolService.saveRol(rol));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}

	@RequestMapping(value = "/updateRol")
	@ResponseBody
	public JsonMessageResult updateRol(Rol rol) {
		try {
			return resultSuccessUpdate(rolService.updateRol(rol));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}

	@RequestMapping(value = "/deleteRol")
	@ResponseBody
	public JsonMessageResult deleteRol(Integer cod_rol) {
		try {
			rolService.deleteRol(cod_rol);
			return resultSuccessDelete(cod_rol);
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}

	@RequestMapping(value = "/buscaRol")
	@ResponseBody
	public JsonMessageResult buscaRol(Integer cod_rol) {
		try {
			return resultSuccessList(rolService.getbyID(cod_rol));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}

	@RequestMapping(value = "/isRolNombreUnique")
	@ResponseBody
	public JsonMessageResult isRolNombreUnique(Rol rol) {
		return resultAlertUnique(rolService.isRolNombreUnique(rol),
					"Nombre",rol.getNombre());
	}
}
