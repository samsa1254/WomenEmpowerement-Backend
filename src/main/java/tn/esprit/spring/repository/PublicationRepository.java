package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Publication;

@Repository
public interface PublicationRepository extends CrudRepository<Publication, Long> {


	@Query(value = "SELECT publication_id_publication FROM commentd GROUP BY publication_id_publication  ORDER BY COUNT(id_com) DESC LIMIT 10",nativeQuery = true)
	public List<Long> Tendency (  );
}
