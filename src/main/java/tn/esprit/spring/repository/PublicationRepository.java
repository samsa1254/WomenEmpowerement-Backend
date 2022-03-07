package tn.esprit.spring.repository;


import java.awt.PageAttributes;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Publication;

@Repository
public interface PublicationRepository extends CrudRepository<Publication, Long> {

	
	@Query(value = "SELECT publication_id_publication FROM commentd GROUP BY publication_id_publication ORDER BY COUNT(id_com) DESC LIMIT 10",nativeQuery = true )
	public List<Long> Tendency (  );
	
	@Query(value = "SELECT publication_id_publication FROM reaction GROUP BY publication_id_publication ORDER BY COUNT(id) DESC LIMIT 10" ,nativeQuery = true)
	public List<Long> MostReacted();
	
	@Query(value = "SELECT r.value FROM Reaction r   WHERE r.publication.idPublication = :idPublication GROUP BY r.value ORDER by COUNT(id) DESC ")
	public List<String>  reacts (@Param("idPublication")  Long idPublication  );
	
}
