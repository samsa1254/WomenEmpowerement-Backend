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

<<<<<<< HEAD
	User findUserByUserName(String userName) ;
=======
	User findUserByLogin ( String Login );
	
    Boolean block(String angry, String blocked);
	
	Boolean unblock(String angry, String blocked);
	
	Boolean blockControl(String angry, String blocked);

	List<User> listeDeUserParexpertspeciality(ExpertSpec spec);
	
	
>>>>>>> 6d893a7494f6e37b59bfe3d5718887f4ef545225

	
}
