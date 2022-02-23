package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Response;
@Repository
public interface ResponceRepository extends CrudRepository<Response, Long>{

}
