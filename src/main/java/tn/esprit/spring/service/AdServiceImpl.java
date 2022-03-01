package tn.esprit.spring.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Ad;

import tn.esprit.spring.repository.AdRepository;

@Service
public class AdServiceImpl implements AdService {

	@Autowired
	private AdRepository AdRep ; 
	
	@Override
	public Ad addad(Ad ad) {
		AdRep.save(ad);
		return ad;
	}

	@Override
	public void deleteAd(Long id) {
		AdRep.deleteById(id);

		
	}

	@Override
	public Ad updateAd(Ad ad) {
		AdRep.save(ad);
		return ad;
	}

	@Override
	public List<Ad> retrieveAllAds() {
		List<Ad> ads = (List<Ad>) AdRep.findAll();
		return ads;
	}

	@Override
	public Ad retrievebyId(Long id) {
		Ad a = AdRep.findById(id).get();
		
		return a;
	}

}
