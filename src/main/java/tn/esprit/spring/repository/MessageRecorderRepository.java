package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.MessageRecorder;

@Repository
public interface MessageRecorderRepository extends CrudRepository<MessageRecorder, Long> {

	List<MessageRecorder> findAllByReceiverName(String receiverName);
}
