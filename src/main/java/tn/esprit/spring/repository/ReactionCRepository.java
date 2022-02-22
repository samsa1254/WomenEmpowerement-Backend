package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.ReactionC;

@Repository
public interface ReactionCRepository extends CrudRepository<ReactionC, Long> {

}
