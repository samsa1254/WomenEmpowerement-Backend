package tn.esprit.spring.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Api
public class CommentD {
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idCom ; 
	private String tenor ; 
	private Date date ; 
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "commentd")
	@JsonIgnore
	private List<ReactionC> reactionc ; 
	
	@ManyToOne
	@JsonIgnore
	private Publication publication ; 
	
	@ManyToOne
	@JsonIgnore
	private User user ; 

}
