package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.CommentD;

@Repository
public interface CommentDRepository  extends CrudRepository<CommentD, Long>{

}
