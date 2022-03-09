package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entities.ExpertSpec;
import tn.esprit.spring.entities.User;


public interface IUserService {

	
	List<User> retrieveAllUser();
	
	User addUser(User c);

	void deleteUser(int id);

	//User updateUser(User c);

	User retrieveUser(int id);

	User updateUser(User c);

	User findUserByUserName(String userName) ;
	User findUserByLogin ( String Login );
	
    Boolean block(String angry, String blocked);
	
	Boolean unblock(String angry, String blocked);
	
	Boolean blockControl(String angry, String blocked);

	List<User> listeDeUserParexpertspeciality(ExpertSpec spec);
	
	public void deleteUselessAcounts();
	
	
	
	
	
	void blockuseraccount(User u);
	void unblockuseraccount(User u);
	
	

	
}
