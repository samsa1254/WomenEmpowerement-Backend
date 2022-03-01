package tn.esprit.spring.repository;




import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Apprenant;


@Repository
public interface ApprenantRepository extends CrudRepository<Apprenant, Long>{

	}
