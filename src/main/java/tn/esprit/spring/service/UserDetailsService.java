package tn.esprit.spring.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import tn.esprit.spring.entities.User;


public interface UserDetailsService {

	UserDetails loadUserByUsername(String username);

	
	
	
}
