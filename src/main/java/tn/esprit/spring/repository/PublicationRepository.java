package tn.esprit.spring.repository;



import java.time.LocalTime;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Publication;

@Repository
public interface PublicationRepository extends CrudRepository<Publication, Long> {

	
	@Query(value = "SELECT c.publication.idPublication FROM CommentD c GROUP BY c.publication.idPublication ORDER BY COUNT(idCom) DESC " )
	public List<Long> Tendency ();
	
	@Query(value = "SELECT r.publication.idPublication FROM Reaction r GROUP BY r.publication.idPublication ORDER BY COUNT(id) DESC ")
	public List<Long> MostReacted();
	
	@Query(value = "SELECT r.value FROM Reaction r   WHERE r.publication.idPublication = :idPublication GROUP BY r.value ORDER by COUNT(id) DESC ")
	public List<String>  reacts (@Param("idPublication")  Long idPublication  );
	
}
