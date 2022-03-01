package tn.esprit.spring.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Api

public class Training {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private Date satartDate;
	private Date endDate;
	//number of peopole who have rated
	private int numberPR;
	private float rating;
	private String description;
	private String file;
	
	
	@OneToMany(mappedBy = "training")
	private List<Certification> certifications ;
	
	
	@OneToMany(mappedBy = "training")
	private List<Comment> comments ;
	
	
	@OneToMany(mappedBy = "training")
	private List<Quiz> quizs ;
	
	
	@ManyToOne 
	private User user ; 
	

	
	

}
