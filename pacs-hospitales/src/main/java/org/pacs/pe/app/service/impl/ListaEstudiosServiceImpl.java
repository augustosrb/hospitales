package org.pacs.pe.app.service.impl;

import java.util.List;

import org.pacs.pe.app.dao.ListaEstudiosDao;
import org.pacs.pe.app.model.Estudio;
import org.pacs.pe.app.service.ListaEstudiosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ListaEstudiosServiceImpl implements ListaEstudiosService {

	@Autowired
	private ListaEstudiosDao ltEstudiosDao;
	
	@Override
	public List<Estudio>  findAllEtudios(Estudio estudio)
	{
		return	ltEstudiosDao.findAllEtudios(estudio);
	}
	
	

	
	
}
