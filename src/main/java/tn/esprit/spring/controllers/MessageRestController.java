package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
 
import tn.esprit.spring.entities.Message;
import tn.esprit.spring.service.MessageService;

@Api(tags = "Message Management")
@CrossOrigin
@RestController
@RequestMapping("/Message")
public class MessageRestController {
	@Autowired
	private MessageService MessageSer ;
	
	@PostMapping("/add-msg")
	@ApiOperation(value = "ajouter un message ")
	@ResponseBody 
	public Message addMessage(@RequestBody Message m )
	{
	
		return  MessageSer.addMsg(m);
		
	}
	
	@DeleteMapping("/remove-msg/{idMesssage}")
	@ApiOperation(value = "supprimer un Message ")
	@ResponseBody
	public void removeMessage(@PathVariable("idMesssage") Long idMesssage )
	{
		MessageSer.deletemsg(idMesssage);
	}
	
	

}
