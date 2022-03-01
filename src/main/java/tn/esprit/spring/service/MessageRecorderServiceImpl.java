package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.MessageRecorder;
import tn.esprit.spring.repository.MessageRecorderRepository;

@Service
public class MessageRecorderServiceImpl implements MessagerecorderService {
	
	@Autowired
	private MessageRecorderRepository MessagerRecorederRep ;

	@Override
	public void save(String sender, String receiver, String messageContent) {
		MessageRecorder messageEntity = new MessageRecorder();
		messageEntity.setSenderName(sender);
		messageEntity.setReceiverName(receiver);
		messageEntity.setMessageContent(messageContent);
		MessagerRecorederRep.save(messageEntity);
		
	}

	@Override
	public List<MessageRecorder> findAllByReceiverName(String receiverName) {
		List<MessageRecorder> myMessages = MessagerRecorederRep.findAllByReceiverName(receiverName);
		return myMessages;
		 
	}

}
