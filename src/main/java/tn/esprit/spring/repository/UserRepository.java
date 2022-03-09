package tn.esprit.spring.repository;

import java.util.List;

import javax.persistence.PostUpdate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.ExpertSpec;
import tn.esprit.spring.entities.User;
import org.springframework.transaction.annotation.Transactional;





@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
 

	
	User findByLogin(String Login) ;
    User findByEmailIgnoreCase(String email);

 
	List<User> findByexpertspeciality(ExpertSpec spec);
	
	
	
	@Modifying
	@Transactional
	@Query("DELETE FROM User c WHERE c.isEnabled= :bool")
	void deleteUselessUsers(@Param("bool") Boolean bool);

}
