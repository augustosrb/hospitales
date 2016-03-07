package org.pacs.pe.app.controller;

import org.pacs.pe.app.model.Estudio;
import org.pacs.pe.app.service.ListaEstudiosService;
import org.pacs.pe.util.JsonMessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pacs")
public class ListaEstudiosController extends AbstractHandler {
	
	@Autowired
	private ListaEstudiosService ltEstudiosService;
	
	@RequestMapping(value = "/lstEstudios")
	public String lstEstudios() {
		return "lstEstudios";
	}
	
	@RequestMapping(value = "/listarEstudios")
	@ResponseBody
	public JsonMessageResult listarEstudios(Estudio estudio) {
		try {
			return resultSuccessList(ltEstudiosService.findAllEtudios(estudio));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}
}
