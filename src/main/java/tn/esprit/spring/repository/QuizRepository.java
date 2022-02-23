package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Quiz;

@Repository
public interface QuizRepository extends CrudRepository<Quiz, Long>{

}
