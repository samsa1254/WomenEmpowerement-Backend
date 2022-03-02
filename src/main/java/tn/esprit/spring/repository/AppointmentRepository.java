package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Appointment;
@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Integer>{

}
