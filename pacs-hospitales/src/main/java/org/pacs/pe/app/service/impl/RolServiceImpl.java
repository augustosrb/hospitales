package org.pacs.pe.app.service.impl;

import java.util.List;

import org.pacs.pe.app.dao.RolDao;
import org.pacs.pe.app.model.Rol;
import org.pacs.pe.app.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RolServiceImpl implements RolService {

	@Autowired
	private RolDao rolDao;

	@Override
	@Transactional(readOnly = true)
	public List<Rol> findAllRoles(Rol rol) throws Exception {
		return rolDao.findAllRoles(rol);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Rol getbyID(Integer cod_rol) throws Exception {
		return rolDao.getbyID(cod_rol);
	}

	@Override
	@Transactional
	public Rol saveRol(final Rol rol) throws Exception {
		return rolDao.saveRol(rol);
	}

	@Override
	@Transactional
	public Rol updateRol(Rol rol) throws Exception {
		return rolDao.updateRol(rol);
	}

	@Override
	@Transactional
	public void deleteRol(Integer cod_rol) throws Exception {
		rolDao.deleteRol(cod_rol);
	}
	
	@Override
	@Transactional(readOnly = true)	
	public Rol findRolByNombre(String nombre)
	{
		return rolDao.findRolByNombre(nombre);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Boolean isRolNombreUnique(Rol rol) {
		return rolDao.isRolNombreUnique(rol);
	}
}
