package tn.esprit.spring.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
public class Publication {
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long idPublication;
	private String post ; 
	private Date date ; 
	private String state ; 
	
	@ManyToMany (mappedBy = "publications")
	@JsonIgnore
	private List<User> users ; 
	
	@OneToMany(mappedBy = "publication")
	private List<Reaction> reactions ; 
	
	@OneToMany (cascade = CascadeType.ALL , mappedBy =  "publication")
	private List<CommentD> commentDs;
	
	
	

}
