package tn.esprit.spring.service;




import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import tn.esprit.spring.entities.User;



@Service
public class MailService {

	

	/*
	 * The Spring Framework provides an easy abstraction for sending email by
	 * using the JavaMailSender interface, and Spring Boot provides
	 * auto-configuration for it as well as a starter module.
	 */
	private JavaMailSender javaMailSender;

	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendEmail(String maill,float amountdonnation , String usermail) throws MailException {

		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(maill);
		mail.setSubject("Testing Mail API");
		mail.setText("Dear Client :"+usermail+" Your donnation with :"+amountdonnation+" has been saved succefully!! ");

		javaMailSender.send(mail);
	}

	
	
	
     public void sendEmail2(String maill, String usermail) throws MailException {

		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(maill);
		mail.setSubject("Testing Mail API");
		mail.setText("Dear Client :"+usermail+" You have been blocked !!!");

		javaMailSender.send(mail);
	}

	
	
	
	public void sendEmailWithAttachment(User user) throws MailException, MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setTo(user.getEmail());
		helper.setSubject("Testing Mail API with Attachment");
		helper.setText("Please find the attached document below.");

		ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
		helper.addAttachment(classPathResource.getFilename(), classPathResource);

		javaMailSender.send(mimeMessage);
	}
}