package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< hamzaa
=======
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.ResponseEntity;
>>>>>>> local
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.service.IUserService;




@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	IUserService UserService;
	
	@GetMapping("/retrieve-all-User")
	@ResponseBody
	public List<User> getUser() {
	List<User> listUser = UserService.retrieveAllUser();
	return listUser;
	}

	@GetMapping("/retrieve-User/{User-id}")
	@ResponseBody
	public User retrieveUser(@PathVariable("User-id") int UserId) {
	return UserService.retrieveUser(UserId);
	}

	@PostMapping("/add-User")
	@ResponseBody
	public User AddUser(@RequestBody User c)
	{
<<<<<<< hamzaa
		User User = UserService.addUser(c);
	return User;
=======
		User userExists = UserService.findUserByUserName(c.getLogin());
		if (userExists != null) {
		String responseMessage = "THIS USER ALREADY EXISTS...!!!";
		System.out.println(responseMessage);		
		return null;
		} 
		else
		{User User = UserService.addUser(c);
		
		if (c.getRole().toString().equals("Admin")||c.getRole().toString().equals("tutor")||c.getRole().toString().equals("expert"))
		{ SimpleMailMessage mailMessage = new SimpleMailMessage();
	     mailMessage.setTo(User.getEmail());
	     mailMessage.setSubject("NEW MEMBERSHIP !");
	     mailMessage.setFrom("womenempowermentesprit@gmail.com");
	     mailMessage.setText("WELCOME ! \n YOU ARE NOW A NEW MEMBER IN WOMEN EMPOWERMENET \n ");

	     emailSenderService.sendEmail(mailMessage);}
		
	return User;}
>>>>>>> local
	}


	@DeleteMapping("/remove-User/{User-id}")
	@ResponseBody
	public void removeUser(@PathVariable("User-id") int UserId) {
		UserService.deleteUser(UserId);
	}


	@PutMapping("/modify-User")
	@ResponseBody
	public User modifyUser(@RequestBody User User) {
	return UserService.updateUser(User);
	}

<<<<<<< hamzaa

=======
	
	
	
	// Mail Sender Config 

	
	
	@PostMapping("/register")
	public String Register ( @RequestBody User user) { 
	String msg="";

	user.setIsEnabled(false);
	User userExists = UserService.findUserByUserName(user.getLogin());
	if (userExists != null ) {
	msg="There is already a user registered with the user name provided";
	} 
	if (!user.getRole().toString().equals("women"))
			{msg = "cette rebrique est consacré que pour les femmes";}
	else {
		
		UserService.addUser(user);
	msg="OK";
	
	
	 ConfirmationToken confirmationToken = new ConfirmationToken(user);

     confirmationTokenRepository.save(confirmationToken);

     SimpleMailMessage mailMessage = new SimpleMailMessage();
     mailMessage.setTo(user.getEmail());
     mailMessage.setSubject("Complete Registration!");
     mailMessage.setFrom("womenempowermentesprit@gmail.com");
     mailMessage.setText("To confirm your account, please click here : "
     +"http://localhost:8089/SpringMVC/user/confirm-account?token="+confirmationToken.getConfirmationToken());

     emailSenderService.sendEmail(mailMessage);
	
	}
	return msg; }

	
	@RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
	
	public void confirmUserAccount(@RequestParam("token")String confirmationToken) {
		 
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

		if(token != null)
	        {
	            User user = UserService.findUserByUserName(token.getUser().getLogin());
	            user.setIsEnabled(true);
	            UserService.updateUser(user);
	            System.out.println("Votre Compte est activee avec succees ");
	        }
		
	}
	    


	
	@PostMapping("/passwordreset/{login}")
	public String passwordreset (@PathVariable("login") String login) { 
	String msg="";
	 int leftLimit = 48; // numeral '0'
     int rightLimit = 122; // letter 'z'
     int targetStringLength = 10;
     Random random = new Random();

     String generatedString = random.ints(leftLimit, rightLimit + 1)
       .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
       .limit(targetStringLength)
       .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
       .toString();
     
	
	
	User user = UserService.findUserByUserName(login);
    user.setPassword(generatedString) ; 


     SimpleMailMessage mailMessage = new SimpleMailMessage();
     mailMessage.setTo(user.getEmail());
     mailMessage.setSubject("PASSWORD RESET !");
     mailMessage.setFrom("womenempowermentesprit@gmail.com");
     mailMessage.setText("YOUR NEW PASSWORD IS : "+generatedString);

     emailSenderService.sendEmail(mailMessage);
	
	return msg; }

	    
	}
>>>>>>> local

}
