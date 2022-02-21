package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Message;

@Repository
public interface MessageRepository  extends CrudRepository<Message, Long>{

}
