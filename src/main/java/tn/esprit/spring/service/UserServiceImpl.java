package tn.esprit.spring.service;


import java.security.Key;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import tn.esprit.spring.entities.Sexe;


import tn.esprit.spring.entities.BlockUser;

import tn.esprit.spring.entities.ExpertSpec;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.*;



@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository UserRepository;
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	//Authentification 
	
	
	public User findUserByUserName(String Login) {
	return UserRepository.findByLogin(Login);
	}
	
	
	
	
	
	
	@Autowired
	BlockUserRepository BlockUserRep ;

	@Autowired
	public UserServiceImpl(UserRepository UserRepository , BlockUserRepository BlockUserRep) {
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
		//c.setPassword(encrypt(c.getPassword()));
		c.setPassword(bCryptPasswordEncoder.encode(c.getPassword()));
		if (c.getRole().toString().equals("Admin"))
		{
			c.setSubscribtion(null);
			c.setTutorspeciality(null);
			c.setTutortype(null);
			c.setExpertadress(null);
			//c.setExpertnumber(null);
			c.setExpertspeciality(null);
			c.setIsEnabled(true);

		}
		
		
		if (c.getRole().toString().equals("women"))
		{
			c.setAdminsector(null);
			c.setTutorspeciality(null);
			c.setTutortype(null);
			c.setExpertadress(null);
			//c.setExpertnumber(null);
			c.setExpertspeciality(null);
			c.setSexe(Sexe.Women);
			c.setIsEnabled(false);

		}
		
		if (c.getRole().toString().equals("tutor"))
		{
			c.setAdminsector(null);
			c.setSubscribtion(null);
			c.setExpertadress(null);
			//c.setExpertnumber(null);
			c.setExpertspeciality(null);
			c.setIsEnabled(true);

		}
		
		if (c.getRole().toString().equals("expert"))
		{
			c.setAdminsector(null);
			c.setSubscribtion(null);
			c.setTutorspeciality(null);
			c.setTutortype(null);
			c.setIsEnabled(true);

		}

		UserRepository.save(c);
		return c ; 
		
		
	}



	@Override
	public User updateUser(User c) {
		//c.setPassword(encrypt(c.getPassword()));
		c.setPassword(bCryptPasswordEncoder.encode(c.getPassword()));
		if (c.getRole().equals("admin"))
		{
			c.setSubscribtion(null);
			c.setTutorspeciality(null);
			c.setTutortype(null);
			c.setExpertadress(null);
			//c.setExpertnumber(null);
			c.setExpertspeciality(null);
		}
		
		
		if (c.getRole().equals("women"))
		{
			c.setAdminsector(null);
			c.setTutorspeciality(null);
			c.setTutortype(null);
			c.setExpertadress(null);
			//c.setExpertnumber(null);
			c.setExpertspeciality(null);
			c.setSexe(Sexe.Women);
		}
		
		if (c.getRole().equals("tutor"))
		{
			c.setAdminsector(null);
			c.setSubscribtion(null);
			c.setExpertadress(null);
			//c.setExpertnumber(null);
			c.setExpertadress(null);
			//c.setExpertnumber(null);
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
	
		
		@Override
	public List<User> listeDeUserParexpertspeciality(ExpertSpec spec){
			return UserRepository.findByexpertspeciality(spec);
			
		}
	
	
		
		
		
		//Function Of deleting unuseful a acounts 
		@Scheduled(cron = "0 1 1 * * ?")
		@Override
		public void deleteUselessAcounts() {
			 UserRepository.deleteUselessUsers(false);
			
			
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





	@Override
	public User findUserByLogin(String Login) {
		User u = UserRepository.findByLogin(Login);
		return u;
	}



	//fcts reliés au blockage et au chat one to one . 

	@Override
	public Boolean block(String angryName, String blockedName) {
		User angry = UserRepository.findByLogin(angryName);
		User blocked = UserRepository.findByLogin(blockedName);
		if(angry != null  && blocked != null) 
		{
			BlockUser blockUser = new BlockUser();
		
			blockUser.setAngryId(angry.getIduser());
			blockUser.setBlockedId(blocked.getIduser());
			BlockUserRep.save(blockUser);
			return true ;
			
		}
		return false;
	}





	@Override
	public Boolean unblock(String angryName, String blockedName) {
		User angry = UserRepository.findByLogin(angryName);
		User blocked = UserRepository.findByLogin(blockedName);
		if(angry != null  && blocked != null) 
		{
			try {
				BlockUserRep.unblock(angry.getIduser(), blocked.getIduser());
			} 
			catch(Exception e){
				return false;
			}
		}
		
		return true;
	} 





	@Override
	public Boolean blockControl(String angryName, String blockedName) {
		User angry = UserRepository.findByLogin(angryName);
		User blocked = UserRepository.findByLogin(blockedName);
		List<BlockUser> listOfBlock = BlockUserRep.findAllByAngryId(angry.getIduser());
		ArrayList<Integer> BlockedIds = new ArrayList<Integer>();
		int nbr = listOfBlock.size();
		for(int flag = 0 ; flag < nbr ; flag++)
		{
			BlockedIds.add(listOfBlock.get(flag).getBlockedId());
		}
		if(BlockedIds.contains(blocked.getIduser()))	
		{
			return true ;
		}
		return false;
	}






	@Override
	public void blockuseraccount(User u) {
		if (u.getIsEnabled()==true) {
           u.setIsEnabled(false);	
           }	
	}






	@Override
	public void unblockuseraccount(User u) {
		if(u.getIsEnabled()==false) {
         u.setIsEnabled(true);
		}
	}
	
	
	
	
}
