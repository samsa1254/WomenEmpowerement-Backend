package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Event;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Long>,JpaSpecificationExecutor<Event> {


}
