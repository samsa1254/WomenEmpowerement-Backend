package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Candidacy;
import tn.esprit.spring.entities.Favorites;

@Repository
public interface FavRepo extends CrudRepository<Favorites, Long> {
	@Query("SELECT c FROM Favorites c where c.user.iduser= :id_user ")
    List<Favorites> getUserFav(@Param("id_user") int id);

}
