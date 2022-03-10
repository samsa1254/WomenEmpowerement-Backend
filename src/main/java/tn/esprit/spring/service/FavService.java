package tn.esprit.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Candidacy;
import tn.esprit.spring.entities.Favorites;

@Service
public interface FavService {
	public Favorites retrievebyID(Long id);
	public List<Favorites> retrieveAll();
	public Favorites Affect( Favorites c , Long ido, int idu);
	public void deleteFav ( Long id );
	public List<Favorites> getFavoritesByIdU(int id);

}
