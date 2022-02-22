package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Reaction;

@Repository
public interface ReactionRepository  extends CrudRepository<Reaction, Long>{

}
