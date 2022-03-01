package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Candidacy;

@Repository
public interface CandidacyRepository extends CrudRepository<Candidacy, Long> {

}
