package tn.esprit.spring.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.CommentD;
 
import tn.esprit.spring.entities.Publication;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.forbiden;
import tn.esprit.spring.repository.CommentDRepository;
import tn.esprit.spring.repository.PublicationRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.repository.forbidenRepository;

@Service
public class CommentDServiceImpl  implements CommentDService{

	@Autowired
	private CommentDRepository CommentRep ;
	@Autowired
	private PublicationRepository PubRep ;
	@Autowired
	private forbidenRepository forbidenRep ;
	@Autowired
	private UserRepository UserRep ;
	
	@Override
	public String AddCommentPub(CommentD commentD, Long idPublication , int id ) {
		Publication p =PubRep.findById(idPublication).get();
		User u =UserRep.findById(id).get(); 
		commentD.setUser(u);
		
		String textbody= commentD.getTenor();
		List<forbiden> badwordlist = (List<forbiden>) forbidenRep.findAll();
		int compteur=0;
		for(int i=0 ; i<badwordlist.size(); i++)
		{
			if (textbody.contains(badwordlist.get(i).getText()))
			{
				compteur++; 
			}
		}
		if (compteur>0)
		{
			return "your Comment contains "+compteur+" bad words";
			
		}
		else	
				{
			
		
			
		    commentD.setPublication(p);
		    CommentRep.save(commentD);
		    return "Comment added successfuly " ;
			 
				}
		
		
	   
		
	}
	
	@Override
	public CommentD addComment(CommentD c) {
		CommentRep.save(c);
		return c ; 
	}

	@Override
	public String updateComment(CommentD commentD) {
		String textbody= commentD.getTenor();
		List<forbiden> badwordlist = (List<forbiden>) forbidenRep.findAll();
		int compteur=0;
		for(int i=0 ; i<badwordlist.size(); i++)
		{
			if (textbody.contains(badwordlist.get(i).getText()))
			{
				compteur++; 
			}
		}
		if (compteur>0)
		{
			return "your Comment contains "+compteur+" bad words";
			
		}
		else	
				{
			
		
		   
		    CommentRep.save(commentD);
			 return "Comment added successfuly " ;
				}
		
	}

	@Override
	public void DeleteComment(Long id) {
		CommentRep.deleteById(id);
		
	}

	@Override
	public CommentD retrievebyId(Long id) {
	CommentD c = CommentRep.findById(id).get();
	return c ; 
	}

	@Override
	public List<CommentD> retrieveAll() {
		List<CommentD> cmnts = (List<CommentD>) CommentRep.findAll();
		return cmnts ;
	}

	
}
