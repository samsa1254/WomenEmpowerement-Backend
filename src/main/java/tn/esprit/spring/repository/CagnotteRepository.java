package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Cagnotte;

@Repository
public interface CagnotteRepository  extends CrudRepository<Cagnotte, Long>{

}
