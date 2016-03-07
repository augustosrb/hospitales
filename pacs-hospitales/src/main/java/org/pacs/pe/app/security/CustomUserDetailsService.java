package org.pacs.pe.app.security;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.pacs.pe.app.model.Rol;
import org.pacs.pe.app.model.Usuario;
import org.pacs.pe.app.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	final static Logger logger = Logger.getLogger(CustomUserDetailsService.class);
    @Autowired
    private LoginService usuarioService;

    static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {    	 
    	
    	Usuario usu = usuarioService.getUsuarioLogin(usuario);
    	
    	List<Rol> listRoles = usuarioService.getRolUsuario(usu.getCod_usuario());
    	
    	List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>(listRoles.size());
   	 	
    	for (Rol rol : listRoles) {
    		authorities.add(new SimpleGrantedAuthority(rol.getNombre()));
   	    }
    
    	
    	    	User user = new User(usu.getCod_usuario(),usu.getNombre() + " " + usu.getApepat() + " " + usu.getApemat(), 
    						usu.getPassword(),authorities);
    	
        return user ;
    }
}