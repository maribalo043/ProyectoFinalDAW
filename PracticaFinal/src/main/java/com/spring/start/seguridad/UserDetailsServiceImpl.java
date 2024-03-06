package com.spring.start.seguridad;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.start.usuarios.Usuario;
import com.spring.start.usuarios.UsuarioDAO;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UsuarioDAO usuarioDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<Usuario> usuario = usuarioDAO.findById(username);
		
		if(usuario.isPresent()) {
			return (UserDetails)usuario.get();
		}
		
		throw new UsernameNotFoundException(username);
		
	}
}
