package tn.esprit.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.MessageRecorder;

@Service
public interface MessagerecorderService {

	void save(String sender, String receiver, String messageContent);
	List<MessageRecorder> findAllByReceiverName(String receiverName);
	
}
