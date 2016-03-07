package org.pacs.pe.app.dao;

import java.util.List;

import org.pacs.pe.app.model.Rol;

public interface RolDao {

	public List<Rol> findAllRoles(Rol rol) throws Exception;

	public Rol getbyID(Integer cod_rol)throws Exception;
	
	public Rol saveRol(final Rol rol) throws Exception;

	public Rol updateRol(Rol rol) throws Exception;

	public void deleteRol(Integer cod_rol) throws Exception;

	public Rol findRolByNombre(String nombre);

	public Boolean isRolNombreUnique(Rol rol);
}
