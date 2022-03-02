package tn.esprit.spring.repository;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entities.Disponibilite;
import tn.esprit.spring.entities.ExpertSpec;
import tn.esprit.spring.entities.User;


public interface DisponibiliteRepository extends JpaRepository<Disponibilite,Long> {
	List<Disponibilite> findBydatedebut(Date dated);
	List<Disponibilite> findBydatefin(Date datef);
	List<Disponibilite>findByDatedebutAfterAndDatefinBefore(Date datedebut, Date datefin);
	


}
