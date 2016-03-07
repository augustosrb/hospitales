package org.pacs.pe.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.pacs.pe.app.model.Menu;
import org.pacs.pe.app.model.Sistema;
import org.pacs.pe.app.security.User;
import org.pacs.pe.app.service.LoginService;
import org.pacs.pe.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@Autowired
	private LoginService loginService;


	@RequestMapping("/home")
	public String index(HttpServletRequest request) {
		Sistema s = new Sistema();
		s.setEstado(Constantes.ESTADO_ACTIVO);

		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		Integer[] perfiles = loginService.getPerfilesUsuario(user
				.getCod_usuario());

		List<Menu> m = loginService.listMenusCompleta(new Menu(), perfiles);

		// Integer cod_perfil = 16;

		// List<Menu> m = menuService.listMenusCompleta(new Menu(),cod_perfil);

		request.getSession().setAttribute("mylist", "Hola");

		/*request.getSession().setAttribute("sistemas",
				sistemaService.listSistemas(s));*/

		request.getSession().setAttribute("menu", m );

		return "index";
	}

}
