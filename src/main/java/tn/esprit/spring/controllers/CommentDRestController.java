package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import tn.esprit.spring.entities.CommentD;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.CommentDService;

@Api(tags = "CommentD Management")
@CrossOrigin
@RestController
@RequestMapping("/CommentD")
public class CommentDRestController {
	
	@Autowired
	 private CommentDService commentDSer ;
	@Autowired
	private UserRepository UserRep ;
	
	@GetMapping("/retrieve-all-cmntsd")
	@ApiOperation(value = "Récupérer la liste des cmntsd ")
	@ResponseBody
	public List<CommentD> getCommentsd()
	{
		List<CommentD> cmnts = commentDSer.retrieveAll();
		return cmnts;
	}
	
	@GetMapping("/retrieve-commentd/{idCom}")
	@ApiOperation(value = "recuperer un Comment  ")
	@ResponseBody
	public CommentD getComment (@PathVariable("idCom") Long idCom)
	{
		return commentDSer.retrievebyId(idCom);   
	}
	
	@PostMapping("/add-affectcomment/{idPublication}")
	@ResponseBody
	public String ajouterEtAffceterCommentairePub( @RequestBody CommentD commentD ,@PathVariable("idPublication") Long idPublication)
	{
		SecurityContext context = SecurityContextHolder.getContext();
	    Authentication auth = context.getAuthentication();
	   User u = UserRep.findByLogin(auth.getName());
	     int id = u.getIduser() ; 
		return commentDSer.AddCommentPub(commentD, idPublication,id);
	}
	
	@PostMapping("/add-cmntd")
	@ApiOperation(value = "ajouter un cmntd ")
	@ResponseBody 
	public CommentD addComd(@RequestBody CommentD c )
	{
		
		return commentDSer.addComment(c) ; 
		}
	
	@DeleteMapping("/remove-cmntd/{idCom}")
	@ApiOperation(value = "supprimer un cmntd ")
	@ResponseBody
	public void removeCmnt(@PathVariable("idCom") Long idCom )
	{
		commentDSer.DeleteComment(idCom);
	}
	
	@PutMapping("/modify-cmntd")
	@ApiOperation(value = "modifier un cmntd ")
	@ResponseBody
	public String modifyCmnt(@RequestBody CommentD c)
	{
		
		return commentDSer.updateComment(c); 
	}
	
	

}
