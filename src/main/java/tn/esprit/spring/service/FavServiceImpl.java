package tn.esprit.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Favorites;
import tn.esprit.spring.entities.Offer;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.FavRepo;
import tn.esprit.spring.repository.OfferRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class FavServiceImpl implements FavService {

	@Autowired
	FavRepo fr;
	@Autowired
	UserRepository ur;
	@Autowired
	OfferRepository or;
	
	@Override
	public Favorites retrievebyID(Long id) {
		
		return fr.findById(id).get();
	}

	@Override
	public List<Favorites> retrieveAll() {
		
		return ((List<Favorites>) fr.findAll());
	}

	@Override
	public Favorites Affect(Favorites c, Long ido, int idu) {
		  
			Offer p = or.findById(ido).get();
			User u = ur.findById(idu).get();
			
			c.setUser(u);
			c.setOffer(p);
			
			fr.save(c);
			return c;
	}

	@Override
	public void deleteFav(Long id) {
		fr.deleteById(id);	
	}

	@Override
	public List<Favorites> getFavoritesByIdU(int id) {
	
		return fr.getUserFav(id);
	}

}
