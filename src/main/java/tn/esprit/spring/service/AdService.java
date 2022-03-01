package tn.esprit.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Ad;

@Service
public interface AdService {

	public Ad addad( Ad ad); 
	public void deleteAd( Long id ); 
	public Ad updateAd( Ad ad);
	public List<Ad> retrieveAllAds();
	public Ad retrievebyId(Long id);
}
