package tn.esprit.spring.service;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Message;

@Service
public interface MessageService {

	public Message addMsg ( Message message);
	public void deletemsg ( Long id);
	
}
