package tn.esprit.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.forbiden;

@Service
public interface forbidenService {

	public void addforbidenword (forbiden forbiden);
	public void updateforbidenword ( forbiden forbiden);
	public List<forbiden> getall( );
	public forbiden getforbidenword (Long id);
	public void deleteforbidenword ( Long id );
	
}
