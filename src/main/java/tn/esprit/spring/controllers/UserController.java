package tn.esprit.spring.controllers;

import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.standard.expression.Each;

import tn.esprit.spring.entities.ConfirmationToken;
import tn.esprit.spring.entities.ExpertSpec;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.ConfirmationTokenRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.EmailSenderService;
import tn.esprit.spring.service.IUserService;



@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

	 @Autowired
	 IUserService UserService;
	
	 
	 
	 @Autowired
	 private UserRepository userRepository;
	 
	 @Autowired
	 private ConfirmationTokenRepository confirmationTokenRepository;

	 @Autowired
	 private EmailSenderService emailSenderService;
	
	
	 
	
	
	 
	  
	  @GetMapping("/retrieve-me")
		@ResponseBody
		public User retrieveMe() {
		  
		  SecurityContext context = SecurityContextHolder.getContext();
	        Authentication auth = context.getAuthentication();
	        User u = userRepository.findByLogin(auth.getName());
	        int UserId = u.getIduser() ; 
	        
		return UserService.retrieveUser(UserId);
		}
	  
	@GetMapping("/retrieve-all-User")
	@ResponseBody
	public List<User> getUser() {
	List<User> listUser = UserService.retrieveAllUser();
	//System.out.print(s) ; 
	return listUser;
	}


	@GetMapping("/retrieve-User/{userid}")
	@ResponseBody
	public User retrieveUser(@PathVariable("userid") int UserId) {
	return UserService.retrieveUser(UserId);
	}

	@PostMapping("/add-User")
	@ResponseBody
	public User AddUser(@RequestBody User c)
	{
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
		else {
			 ConfirmationToken confirmationToken = new ConfirmationToken(User);

		     confirmationTokenRepository.save(confirmationToken);

		     SimpleMailMessage mailMessage = new SimpleMailMessage();
		     mailMessage.setTo(User.getEmail());
		     mailMessage.setSubject("Complete Registration!");
		     mailMessage.setFrom("womenempowermentesprit@gmail.com");
		     mailMessage.setText("To confirm your account, please click here : "
		     +"http://localhost:8089/SpringMVC/user/confirm-account?token="+confirmationToken.getConfirmationToken());

		     emailSenderService.sendEmail(mailMessage);
		}
		
	return User;}
	}


	@DeleteMapping("/remove-User/{userid}")
	@ResponseBody
	public void removeUser(@PathVariable("userid") int UserId) {
		UserService.deleteUser(UserId);
	}


	@PutMapping("/modify-User")
	@ResponseBody
	public User modifyUser(@RequestBody User User) {
	return UserService.updateUser(User);
	}
	
	
	
	
	@DeleteMapping("/Delete-Useless-User/")
	@ResponseBody
	public void deleteUselessUser() {
		UserService.deleteUselessAcounts ();
	}
	
	
	
	
	
	

	@GetMapping("/blockUser")
	public ResponseEntity<Void> block(@RequestParam String angryUser, @RequestParam String blockedUser) throws Exception {
		UserService.block(angryUser, blockedUser);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/unblockUser")
	public ResponseEntity<Void> unblock(@RequestParam String angryUser, @RequestParam String blockedUser) {
		Boolean b = UserService.unblock(angryUser, blockedUser);
		if(b.equals(Boolean.TRUE)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();


		}}

	@GetMapping("/liste-expertbyspeciality/{speciality}")
	List<User> listeDeexpertParspecialite(@PathVariable("speciality") ExpertSpec spec){
		return UserService.listeDeUserParexpertspeciality(spec);

	}

	
	
	
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
    this.UserService.updateUser(user);
     SimpleMailMessage mailMessage = new SimpleMailMessage();
     mailMessage.setTo(user.getEmail());
     mailMessage.setSubject("PASSWORD RESET !");
     mailMessage.setFrom("womenempowermentesprit@gmail.com");
     mailMessage.setText("YOUR NEW PASSWORD IS : "+generatedString);

     emailSenderService.sendEmail(mailMessage);
	
	return msg; }

	    
	}

