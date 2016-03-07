package org.pacs.pe.app.controller;

import org.pacs.pe.app.model.Estudio;
import org.pacs.pe.app.service.ListaTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pacs")
public class PDFController {

	@Autowired
	private ListaTrabajoService ltTrabajoService;
	
	
	@RequestMapping(value = "/downloadPDF/{cod_estudio}", method = RequestMethod.GET)
	public ModelAndView downloadExcel(@PathVariable int cod_estudio) {
		
		Estudio estudio = ltTrabajoService.getEstudiobyID(cod_estudio);
		
		return new ModelAndView("pdfView", "estudio", estudio);
	}
}
