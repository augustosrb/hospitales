package org.pacs.pe.app.service.impl;

import java.util.List;

import org.pacs.pe.app.dao.SistemaDao;
import org.pacs.pe.app.model.Sistema;
import org.pacs.pe.app.service.SistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SistemaServiceImpl implements SistemaService {

	@Autowired
	private SistemaDao sistemaDao;

	@Override
	@Transactional(readOnly = true)
	public List<Sistema> findAllSistemas(Sistema sistema) throws Exception {
		return sistemaDao.findAllSistemas(sistema);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Sistema getbyID(Integer cod_sistema) throws Exception {
		return sistemaDao.getbyID(cod_sistema);
	}

	@Override
	@Transactional
	public Sistema saveSistema(final Sistema sistema) throws Exception {
		return sistemaDao.saveSistema(sistema);
	}

	@Override
	@Transactional
	public Sistema updateSistema(Sistema sistema) throws Exception {
		return sistemaDao.updateSistema(sistema);
	}

	@Override
	@Transactional
	public void deleteSistema(Integer cod_sistema) throws Exception {
		sistemaDao.deleteSistema(cod_sistema);
	}
	
	@Override
	@Transactional(readOnly = true)	
	public Sistema findSistemaByNombre(String nombre)
	{
		return sistemaDao.findSistemaByNombre(nombre);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Boolean isSistemaNombreUnique(Sistema sistema) {
		return sistemaDao.isSistemaNombreUnique(sistema);
	}
}
