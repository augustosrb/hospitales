package org.pacs.pe.app.controller;

import org.pacs.pe.app.model.Estudio;
import org.pacs.pe.app.service.ListaTrabajoService;
import org.pacs.pe.util.JsonMessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pacs")
public class ListaTrabajoController extends AbstractHandler {
	
	@Autowired
	private ListaTrabajoService ltTrabajoService;
	
	@RequestMapping(value = "/lstTrabajos")
	public String lstTrabajos() {
		return "lstTrabajos";
	}
	
	@RequestMapping(value = "/findAllTrabajos")
	@ResponseBody
	public JsonMessageResult findAllTrabajos(Estudio estudio) {
		try {
			return resultSuccessList(ltTrabajoService.findAllTrabajos(estudio));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}	
	
	@RequestMapping(value = "/diagnosticar")
	@ResponseBody
	public JsonMessageResult diagnosticar(Estudio estudio) {
		try {
			return resultSuccessList(ltTrabajoService.diagnosticar(estudio));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/getEstudiobyID")
	@ResponseBody
	public JsonMessageResult getEstudiobyID(Integer cod_estudio) {
		try {
			return resultSuccessList(ltTrabajoService.getEstudiobyID(cod_estudio));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}
	
	
	
	@RequestMapping(value = "/desbloqueoEstudio")
	@ResponseBody
	public JsonMessageResult desbloqueoEstudio(Estudio estudio) {
		try {
			return resultSuccessList(ltTrabajoService.desbloqueoEstudio(estudio));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}
	

	

}
