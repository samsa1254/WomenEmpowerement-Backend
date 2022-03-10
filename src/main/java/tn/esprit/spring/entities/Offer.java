package tn.esprit.spring.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

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
public class Offer {
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "Id_offer")
	private long id;
	
	private String name;
	
	private String type;
		
	private String status;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date date_Interview;
	
	private float salary;
	
	
	private String location;
	
	//@OneToMany(mappedBy = "offer")
	//private List<Favorites> favorites;
	@JsonIgnore
	@ManyToMany(mappedBy = "offers")
	private List<User> users ; 
	@JsonIgnore
	@OneToMany(mappedBy = "offer")
	private List<Candidacy> Candidacies ;

}
