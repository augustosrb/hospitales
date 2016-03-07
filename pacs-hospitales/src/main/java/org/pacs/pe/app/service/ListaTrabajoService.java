package org.pacs.pe.app.service;

import java.util.List;

import org.pacs.pe.app.model.Estudio;

public interface ListaTrabajoService {
	
	public Estudio diagnosticar(Estudio estudio);

	public List<Estudio> findAllTrabajos(Estudio estudio);
	
	public Estudio getEstudiobyID(Integer cod_estudio);

	public Estudio bloqueoEstudio(Integer cod_estudio);

	public Estudio desbloqueoEstudio(Estudio estudio);

}
