package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Candidacy;
import tn.esprit.spring.entities.Offer;


@Repository
public interface OfferRepository extends CrudRepository<Offer, Long> {

	@Query("SELECT c FROM Offer c where c.name= :name ")
	List<Offer> getOfferbyName(@Param("name") String name);
	
}
