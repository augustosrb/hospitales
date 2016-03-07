package org.pacs.pe.app.controller;

import org.pacs.pe.app.model.Usuario;
import org.pacs.pe.app.service.UsuarioService;
import org.pacs.pe.util.JsonMessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pacs")
public class UsuarioController extends AbstractHandler {

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/mntUsuarios")
	public String mntUsuarioes(Usuario usuario) {
		return "mntUsuarios";
	}

	@RequestMapping(value = "/listarUsuarios")
	@ResponseBody
	public JsonMessageResult listarUsuarios(Usuario usuario) {
		try {
			return resultSuccessList(usuarioService.findAllUsuarios(usuario));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}

	@RequestMapping(value = "/saveUsuario")
	@ResponseBody
	public JsonMessageResult saveUsuario(Usuario usuario) {
		try {
			return resultSuccessInsert(usuarioService.saveUsuario(usuario));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}

	@RequestMapping(value = "/updateUsuario")
	@ResponseBody
	public JsonMessageResult updateUsuario(Usuario usuario) {
		try {
			return resultSuccessUpdate(usuarioService.updateUsuario(usuario));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}

	@RequestMapping(value = "/deleteUsuario")
	@ResponseBody
	public JsonMessageResult deleteUsuario(Integer cod_usuario) {
		try {
			usuarioService.deleteUsuario(cod_usuario);
			return resultSuccessDelete(cod_usuario);
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}

	@RequestMapping(value = "/buscaUsuario")
	@ResponseBody
	public JsonMessageResult buscaUsuario(Integer cod_usuario) {
		try {
			return resultSuccessList(usuarioService.getbyID(cod_usuario));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}

	@RequestMapping(value = "/isUsuarioNombreUnique")
	@ResponseBody
	public JsonMessageResult isUsuarioNombreUnique(Usuario usuario) {
		return resultAlertUnique(usuarioService.isUsuarioNombreUnique(usuario),
					"Nombre",usuario.getNombre());
	}
	
	@RequestMapping(value = "/getLoginUsuario")
	public @ResponseBody JsonMessageResult getLoginUsuario(Usuario usuario) {
		try {
			return resultSuccessList(usuarioService.getLoginUsuario(usuario));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}
	
	@RequestMapping(value="/registrarAsocPerfil")
	public @ResponseBody JsonMessageResult registrarAsocPerfil
	(@RequestParam(value = "perfilsInt") Integer [] perfilsInt, @RequestParam(value = "cod_usuario")  Integer cod_usuario){
		
		try {
			usuarioService.registrarAsocPerfil(perfilsInt,cod_usuario);
			return resultSuccessInsert(perfilsInt);
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}
	
	@RequestMapping(value="/registrarAsocRol")
	public @ResponseBody JsonMessageResult registrarAsocRol
	(@RequestParam(value = "rolsInt") Integer [] rolsInt, @RequestParam(value = "cod_usuario")  Integer cod_usuario){
		
		try {
			usuarioService.registrarAsocRol(rolsInt,cod_usuario);
			return resultSuccessInsert(rolsInt);			
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/getPerfilUsuario")
	public @ResponseBody JsonMessageResult getPerfilUsuario(Usuario cod_usuario) {
		try {
			
			return resultSuccessInsert(usuarioService.getPerfilUsuario(cod_usuario));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}
	@RequestMapping(value = "/getRolUsuario")
	public @ResponseBody JsonMessageResult getRolUsuario(Usuario cod_usuario) {
		try {
			
			
			return resultSuccessInsert(usuarioService.getRolUsuario(cod_usuario));
		} catch (Exception e) {
			return resultFail(e.getMessage());
		}
	}
}
