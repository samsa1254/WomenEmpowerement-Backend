package tn.esprit.spring.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class EmailControllers {
	
	 @Autowired
		JavaMailSender mailSender;

	  
	 /*@GetMapping("/test")
		public String send(){
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("ahmed.nebli@esprit.tn");
			message.setTo("Slavdorn4@gmail.com");
			message.setSubject("subject of the message");
			message.setText("Content of the message");
			
			mailSender.send(message);
			
			return "done";
		}*/
	  
	
	  public String ApplicationMail(String Mail , String candid , String Off , String datea)
	  {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("Slavdorn4@gmail.com");
			message.setTo(Mail);
			message.setText("Mr/Mrs : "+candid+" \n "+" Your application for : " + Off + " \n " +"At :" +datea+ " : Has been successful !");
			message.setSubject("Woman Empowerement Mailing Bot");
			mailSender.send(message);
		 
		    return "Successfully sent";
	  }
	  
	  public String AcceptedMail(String Mail , String candid , String Off)
	  {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("Slavdorn4@gmail.com");
			message.setTo(Mail);
			message.setText("Mr/Mrs"+candid+"\n"+"Your application to" + Off + "Has been approved !");
			message.setSubject("Woman Empowerement Mailing Bot");
			mailSender.send(message);
		 
		    return "Successfully sent";
	  }



}
