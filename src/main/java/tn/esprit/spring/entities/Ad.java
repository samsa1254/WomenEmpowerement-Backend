package tn.esprit.spring.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
public class Ad {
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String name ; 
	private String canal ; 
	private Date datebegin ;
	private Date dateend ; 
	private int targetNum ; 
	private int targetReach ;
	private Float cost ; 
	private String type ; 
	
	@ManyToOne
	private User user ;
}
