package tn.esprit.spring.service;


import java.security.Key;
import java.util.Iterator;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.*;



@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository UserRepository;

	@Autowired
	public UserServiceImpl(UserRepository UserRepository) {
		super();
	}


	
	
	
	@Override
	public List<User> retrieveAllUser() {
		// TODO Auto-generated method stub
		List<User> l = (List<User>) UserRepository.findAll();
		 for (User u : l) {
			 u.setPassword(decrypt(u.getPassword()));
	        }
			return l;

		//return (List<User>) UserRepository.findAll();
	}
	

	@Override
	public User retrieveUser(int id) {
	
		User u = UserRepository.findById(id).orElse(new User());
		
		u.setPassword(decrypt(u.getPassword()));
		
		return u ; 
	}
	

	@Override
	public User addUser(User c) {
		c.setPassword(encrypt(c.getPassword()));
		
		if (c.getRole().toString().equals("admin"))
		{
			c.setSubscribtion(null);
			c.setTutorspeciality(null);
			c.setTutortype(null);
			c.setExpertadress(null);
			c.setExpertnumber(null);
			c.setExpertspeciality(null);
		}
		
		
		if (c.getRole().toString().equals("women"))
		{
			c.setAdminsector(null);
			c.setTutorspeciality(null);
			c.setTutortype(null);
			c.setExpertadress(null);
			c.setExpertnumber(null);
			c.setExpertspeciality(null);
		}
		
		if (c.getRole().toString().equals("tutor"))
		{
			c.setAdminsector(null);
			c.setSubscribtion(null);
			c.setExpertadress(null);
			c.setExpertnumber(null);
			c.setExpertspeciality(null);
		}
		
		if (c.getRole().toString().equals("expert"))
		{
			c.setAdminsector(null);
			c.setSubscribtion(null);
			c.setTutorspeciality(null);
			c.setTutortype(null);
			
		}

		UserRepository.save(c);
		return c ; 
		
		
	}



	@Override
	public User updateUser(User c,int id) {
		c.setPassword(encrypt(c.getPassword()));
		c = UserRepository.findById(id).orElse(null) ; 
		if (c.getRole().equals("admin"))
		{
			c.setSubscribtion(null);
			c.setTutorspeciality(null);
			c.setTutortype(null);
			c.setExpertadress(null);
			c.setExpertnumber(null);
			c.setExpertspeciality(null);
		}
		
		
		if (c.getRole().equals("women"))
		{
			c.setAdminsector(null);
			c.setTutorspeciality(null);
			c.setTutortype(null);
			c.setExpertadress(null);
			c.setExpertnumber(null);
			c.setExpertspeciality(null);
		}
		
		if (c.getRole().equals("tutor"))
		{
			c.setAdminsector(null);
			c.setSubscribtion(null);
			c.setExpertadress(null);
			c.setExpertnumber(null);
			c.setExpertspeciality(null);
		}
		
		if (c.getRole().equals("expert"))
		{
			c.setAdminsector(null);
			c.setSubscribtion(null);
			c.setTutorspeciality(null);
			c.setTutortype(null);
			
		}
		
		UserRepository.save(c) ;
		return c;
	}


		@Override
	public void deleteUser(int id) {
		UserRepository.deleteById(id);
	}
	
	
	
	
	
	
	
	//Fcts de Cryptage et decryptage de mot de passe
	
	public String encrypt(String password){
        String crypte="";
        for (int i=0; i<password.length();i++)  {
            int c=password.charAt(i)^48; 
            crypte=crypte+(char)c;
        }
        return crypte;
    }
	
	 public String decrypt(String password){
	        String aCrypter="";
	        for (int i=0; i<password.length();i++)  {
	            int c=password.charAt(i)^48; 
	            aCrypter=aCrypter+(char)c;
	        }
	        return aCrypter;
	    }
}
