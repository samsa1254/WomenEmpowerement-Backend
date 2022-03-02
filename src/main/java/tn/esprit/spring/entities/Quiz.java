package tn.esprit.spring.entities;

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
public class Quiz {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private int level ;
	private String title;
	//numberOf question
	private int numberQ;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.REMOVE , mappedBy = "quiz")
	private  List<Question> questions ;
	@JsonIgnore
	@ManyToOne
	private Training training ; 

}
