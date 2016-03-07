package org.pacs.pe.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.pacs.pe.app.dao.ListaTrabajoDao;
import org.pacs.pe.app.model.Estudio;
import org.pacs.pe.app.service.ListaTrabajoService;
import org.pacs.pe.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ListaTrabajoServiceImpl implements ListaTrabajoService {

	@Autowired
	private ListaTrabajoDao ltTrabajoDao;

	@Override
	public Estudio diagnosticar(Estudio estudio) {
		return ltTrabajoDao.diagnosticar(estudio);
	}

	@Override
	public List<Estudio> findAllTrabajos(Estudio estudio) {
		return ltTrabajoDao.findAllTrabajos(estudio);
	}

	@Override
	public Estudio getEstudiobyID(Integer cod_estudio) {
		
		Estudio estudio = new Estudio();
		estudio = ltTrabajoDao.getEstudiobyID(cod_estudio);
		if(estudio.getEstado()==Constantes.ESTADO_RECIBIDO)
		{
			estudio.setNewEstado(Constantes.ESTADO_BLOQUEADO);
			estudio.setDescNewEstado(Constantes.DESCESTADO_BLOQUEADO);
			bloqueoEstudio(cod_estudio);	
		}
		
		return estudio;
		
		
	}

	@Override
	public Estudio bloqueoEstudio(Integer cod_estudio) {
		return ltTrabajoDao.bloqueoEstudio(cod_estudio);
	}

	@Override
	public Estudio desbloqueoEstudio(Estudio estudio) {
		return ltTrabajoDao.desbloqueoEstudio(estudio);
	}

}
