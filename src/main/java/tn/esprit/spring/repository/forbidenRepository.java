package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.forbiden;

@Repository
public interface forbidenRepository extends CrudRepository<forbiden, Long> {

}
