package tn.esprit.spring.repository;

import java.util.List;

 
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.BlockUser;

@Repository
public interface BlockUserRepository  extends CrudRepository<BlockUser, Long>{
	
	List<BlockUser> findAllByAngryId(int angryId);
	
	@Transactional
	@Modifying
	@Query("delete from BlockUser b where b.angryId = :angryId and b.blockedId = :blockedId")
	void unblock(int angryId , int blockedId);

}
