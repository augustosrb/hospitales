package org.pacs.pe.app.service.impl;

import java.util.List;

import org.pacs.pe.app.dao.UsuarioDao;
import org.pacs.pe.app.model.Perfil;
import org.pacs.pe.app.model.Rol;
import org.pacs.pe.app.model.Usuario;
import org.pacs.pe.app.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAllUsuarios(Usuario usuario) throws Exception {
		return usuarioDao.findAllUsuarios(usuario);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Usuario getbyID(Integer cod_usuario) throws Exception {
		return usuarioDao.getbyID(cod_usuario);
	}

	@Override
	@Transactional
	public Usuario saveUsuario(final Usuario usuario) throws Exception {
		return usuarioDao.saveUsuario(usuario);
	}

	@Override
	@Transactional
	public Usuario updateUsuario(Usuario usuario) throws Exception {
		return usuarioDao.updateUsuario(usuario);
	}

	@Override
	@Transactional
	public void deleteUsuario(Integer cod_usuario) throws Exception {
		usuarioDao.deleteUsuario(cod_usuario);
	}
	
	@Override
	@Transactional(readOnly = true)	
	public Usuario findUsuarioByNombre(String nombre)
	{
		return usuarioDao.findUsuarioByNombre(nombre);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Boolean isUsuarioNombreUnique(Usuario usuario) {
		return usuarioDao.isUsuarioNombreUnique(usuario);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Usuario getLoginUsuario(Usuario usuario) {
		return usuarioDao.getLoginUsuario(usuario);
	}

	@Override
	@Transactional
	public void registrarAsocPerfil(Integer[] perfilsInt, Integer cod_usuario) {
		usuarioDao.registrarAsocPerfil(perfilsInt,cod_usuario);
	}

	@Override
	@Transactional
	public void registrarAsocRol(Integer[] rolsInt, Integer cod_usuario) {
		usuarioDao.registrarAsocRol(rolsInt,cod_usuario);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Perfil> getPerfilUsuario(Usuario usuario) {
		return usuarioDao.getPerfilUsuario(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Rol> getRolUsuario(Usuario usuario) {
		return usuarioDao.getRolUsuario(usuario);
	}
}
