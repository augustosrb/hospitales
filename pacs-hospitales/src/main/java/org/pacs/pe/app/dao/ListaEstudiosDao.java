package org.pacs.pe.app.dao;

import java.util.List;

import org.pacs.pe.app.model.Estudio;

public interface ListaEstudiosDao {

	public List<Estudio> findAllEtudios(Estudio estudio);

}
