package tn.esprit.spring.service;


import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.UserDetailsImpl;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.security.UserSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private IUserService userService;
	

	

	public boolean hasUserId(Authentication authentication, int userId) {
		
		int userID=userRepo.findByLogin(authentication.getName()).getIduser();
		User user = userRepo.findById(userID).orElse(null);
//		System.out.println(userId+"  "+userID);
            if(userID==userId && user.getIsEnabled()==true)
            	return true;
            
            return false;
       
    }
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=userRepo.findByLogin(username);
		
//		System.out.println("inside impl "+user);
		
		if(user==null) {
			System.out.println("exception thrown");
			throw new UsernameNotFoundException(username + "not found");
		}
		return new UserDetailsImpl(user);
	}  


	
}