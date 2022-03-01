package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Message;
import tn.esprit.spring.repository.MessageRepository;

@Service
public class MessageServiceImpl  implements MessageService{

	
	@Autowired
	private MessageRepository MessageRep ; 
	@Override
	public Message addMsg(Message message) {
		MessageRep.save(message);
		return message ;
	}

	@Override
	public void deletemsg(Long id) {
		MessageRep.deleteById(id);
		
	}

}
