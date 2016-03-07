package org.pacs.pe.app.dao;

import java.util.List;

import org.pacs.pe.app.model.Sistema;

public interface SistemaDao {

	public List<Sistema> findAllSistemas(Sistema sistema) throws Exception;

	public Sistema getbyID(Integer cod_sistema)throws Exception;
	
	public Sistema saveSistema(final Sistema sistema) throws Exception;

	public Sistema updateSistema(Sistema sistema) throws Exception;

	public void deleteSistema(Integer cod_sistema) throws Exception;

	public Sistema findSistemaByNombre(String nombre);

	public Boolean isSistemaNombreUnique(Sistema sistema);
}
