package tn.esprit.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;



@Component("userSecurity")
public class UserSecurity {
	
	@Autowired
	UserRepository userRepo;

	public boolean hasUserId(Authentication authentication, int userId) {
		
		int userID=userRepo.findByLogin(authentication.getName()).getIduser();
		User user = userRepo.findById(userID).orElse(null);
//		System.out.println(userId+"  "+userID);
            if(userID==userId && user.getIsEnabled()==true)
            	return true;
            
            return false;
       
    }
	
	
	
	
public boolean hasUserIdd(Authentication authentication) {
		
		int userID=userRepo.findByLogin(authentication.getName()).getIduser();
		System.out.print(userID);
		User user = userRepo.findById(userID).orElse(null);
		System.out.print(user.getIduser());

//		System.out.println(userId+"  "+userID);
            if(user.getIsEnabled()==true)
            	return true;
            
            return false;
       
    }
}