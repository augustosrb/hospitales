package org.pacs.pe.app.controller;

import org.pacs.pe.app.model.Sistema;
import org.pacs.pe.app.service.SistemaService;
import org.pacs.pe.util.JsonMessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pacs")
public class SistemaController extends AbstractHandler {

	@Autowired
	private SistemaService sistemaService;

	@RequestMapping(value = "/mntSistemas")
	public String mntSistemas(Sistema sistema) {
		return "mntSistemas";
	}

	@RequestMapping(value = "/listarSistemas")
	@ResponseBody
	public JsonMessageResult listarSistemas(Sistema sistema) {
		try {
			return resultSuccessList(sistemaService.findAllSistemas(sistema));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}

	@RequestMapping(value = "/saveSistema")
	@ResponseBody
	public JsonMessageResult saveSistema(Sistema sistema) {
		try {
			return resultSuccessInsert(sistemaService.saveSistema(sistema));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}

	@RequestMapping(value = "/updateSistema")
	@ResponseBody
	public JsonMessageResult updateSistema(Sistema sistema) {
		try {
			return resultSuccessUpdate(sistemaService.updateSistema(sistema));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}

	@RequestMapping(value = "/deleteSistema")
	@ResponseBody
	public JsonMessageResult deleteSistema(Integer cod_sistema) {
		try {
			sistemaService.deleteSistema(cod_sistema);
			return resultSuccessDelete(cod_sistema);
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}

	@RequestMapping(value = "/buscaSistema")
	@ResponseBody
	public JsonMessageResult buscaSistema(Integer cod_sistema) {
		try {
			return resultSuccessList(sistemaService.getbyID(cod_sistema));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}

	@RequestMapping(value = "/isSistemaNombreUnique")
	@ResponseBody
	public JsonMessageResult isSistemaNombreUnique(Sistema sistema) {
		return resultAlertUnique(sistemaService.isSistemaNombreUnique(sistema),
					"Nombre",sistema.getNombre());
	}
}
