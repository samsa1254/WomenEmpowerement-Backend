package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Candidacy;

@Repository
public interface CandidacyRepository extends CrudRepository<Candidacy, Long>  {
	@Query("SELECT c FROM Candidacy c where c.offer.id= :id_offer ")
	List<Candidacy> getCandidacyByOffer(@Param("id_offer") Long idoff);
	
	@Query("SELECT c FROM Candidacy c where c.CandidName= :name AND c.State= :state  ")
	List<Candidacy> getCandidState(@Param("name") String name , @Param("state") String state);


}
