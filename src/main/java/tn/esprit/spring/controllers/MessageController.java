package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tn.esprit.spring.entities.MessageEntity;
import tn.esprit.spring.entities.MessageRecorder;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.service.IUserService;
import tn.esprit.spring.service.MessagerecorderService;

@Api(tags = "Msg Management")
@RestController
@CrossOrigin
@RequestMapping("/chat")

public class MessageController {

	@Autowired
	private IUserService UserSer ;
	@Autowired
	private MessagerecorderService MessagerecorderSer ;
	@Autowired
	private SimpMessagingTemplate Simpmsgtemp ;
	
	@MessageMapping("/chat/{to}")
	@ResponseBody
	 public void sendMessage(@DestinationVariable String to, MessageEntity message)
	 {
		User destination = UserSer.findUserByLogin(to);
		User sender = UserSer.findUserByLogin(message.getFromLogin());
		int control = 0 ;
		if ( destination !=  null)
		{
			try {
				Boolean blockControl = UserSer.blockControl(destination.getLogin(), sender.getLogin());
				if(blockControl.equals(Boolean.TRUE))
				{
					control = 1 ; 
					throw new Exception("You can not sent message" + to);
				}
			}
			catch (Exception e) {
				if(control==0) {
					Simpmsgtemp.convertAndSend("/topic/messages/" + to, message);
        			MessagerecorderSer.save(message.getFromLogin(), destination.getLogin(), message.getMessage());			
        			}
				control = 1 ;
		}
			
	 
		if(control==0) {
			Simpmsgtemp.convertAndSend("/topic/messages/" + to, message);
			MessagerecorderSer.save(message.getFromLogin(), destination.getLogin(), message.getMessage());
		}
		}
		
	
}
	@GetMapping("/Mymessages")
	@ResponseBody
	public List<MessageRecorder> getmessages(@RequestParam String receiverName ) throws Exception
	{
		User user  = UserSer.findUserByLogin(receiverName);
		
		
		if(user == null) {
			throw new Exception("There is no user with this " + receiverName + "Login!");
		}
		List<MessageRecorder> response = MessagerecorderSer.findAllByReceiverName(receiverName);
		return response ;
		
	}
	}
