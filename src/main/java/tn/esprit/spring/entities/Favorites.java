package tn.esprit.spring.entities;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
public class Favorites {
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "Id_Fav")
	private long id;
	
	
	@ManyToOne
	private Offer offer;
	
	@JsonIgnore
	@ManyToOne
	private User user ; 
	
	
}
