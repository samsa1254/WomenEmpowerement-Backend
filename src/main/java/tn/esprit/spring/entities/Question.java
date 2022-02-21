package tn.esprit.spring.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
public class Question {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id ;
	private String content;
	//number Responce
	private int numberR;
	
	@OneToMany(cascade = CascadeType.REMOVE , mappedBy = "question")
	private List<Response> responses ;
	
	@ManyToOne
	private Quiz quiz ; 
	

}
