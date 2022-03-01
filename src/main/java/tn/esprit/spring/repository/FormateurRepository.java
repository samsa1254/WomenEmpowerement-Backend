package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Formateur;

@Repository
public interface FormateurRepository  extends CrudRepository<Formateur, Long>{

}
