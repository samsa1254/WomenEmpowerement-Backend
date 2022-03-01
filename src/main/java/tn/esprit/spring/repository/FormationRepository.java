package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Formation;

@Repository

public interface FormationRepository extends CrudRepository<Formation, Long> {

}
