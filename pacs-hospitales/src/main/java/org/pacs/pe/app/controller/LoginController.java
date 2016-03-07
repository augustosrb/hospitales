package org.pacs.pe.app.controller;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	
	@RequestMapping("/")
	public String login()
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		PasswordEncoder encoderMd5 = new Md5PasswordEncoder();
		System.out.println("CLAVE : " + encoder.encode("admin"));
		
		return "login";
		
	}

}
