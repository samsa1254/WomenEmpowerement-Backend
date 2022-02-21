package tn.esprit.spring.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Api
public class Favorites {

	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "Id_favorites")
	private long id;
	
	private String status;
    
	
	private Date date_FVR;
	
	@ManyToOne
	private Offer offer ; 
	
	@ManyToOne
	private User user ; 
}
