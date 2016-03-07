package org.pacs.pe.app.service;

import java.util.List;

import org.pacs.pe.app.model.Sistema;

public interface SistemaService {
	
	public List<Sistema> findAllSistemas(Sistema sistema) throws Exception;
	
	public Sistema getbyID(Integer cod_sistema) throws Exception; 
	
	public Sistema saveSistema(final Sistema sistema) throws Exception;
	
	public Sistema updateSistema(Sistema sistema) throws Exception;
	
	public void deleteSistema(Integer cod_sistema) throws Exception;
	
	public Sistema findSistemaByNombre(String nombre)  throws Exception;
	
	public Boolean isSistemaNombreUnique(Sistema sistema);
	
	

}
