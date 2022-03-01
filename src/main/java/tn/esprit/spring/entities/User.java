package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

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
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Api
public class User implements Serializable{

	
	// Basic For All users 
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int iduser;
	
	@Column(nullable = false)
	private String name ; 
	
	@Column(nullable = false)
	private String lastname ; 
	
	@Column(nullable = false)
	private Date datedenaissaince ; 
	
	@Column(nullable = false)
	private long cin ; 
	
	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
	private Sexe sexe; 
	
	@Column(nullable = false)
	private String email ; 
	
	@Column(nullable = false)
	private String login ; 
	
	@Column(nullable = false)
	private String password ; 
		
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role; 
	
	
	//For Women Only 

	@Column(nullable = true)
	private Date subscribtion ; 

	
	
	// For Admin 
	
	@Column(nullable = true)
	private String adminsector ; 
	

	
	// For Tutor 
	
	@Column(nullable = true)
	private String tutorspeciality ; 
	
	
	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
	private TutorType tutortype; 
	
	
	
	// For Expert 
	
	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
	private ExpertSpec expertspeciality; 
	
	
	@Column(nullable = true)
	private String expertadress ; 
	
	@Column(nullable = true)
	private String expertnumber ; 
	
	

	
	// Relations with Other Entities 
	
	@JsonIgnore
	@OneToMany(mappedBy ="user" )
	private List<Ad> ads  ; 
	
	@JsonIgnore
	@OneToMany(mappedBy ="user" )
	private List<Message> messages  ; 
	
	@JsonIgnore
	@OneToMany(mappedBy ="user" )
	private List<Reaction> reactions  ; 
	
	@JsonIgnore
	@OneToMany(mappedBy ="user" )
	private List<Appointment> appointments  ; 
	

	
	@JsonIgnore
	@OneToMany(mappedBy ="user" )
	private List<Favorites> favorites  ; 
	
	@JsonIgnore
	@OneToMany (mappedBy = "user")
	private List<Report> reports ;
	
	@JsonIgnore
	@ManyToMany
	private List<Event> events;
	
	@JsonIgnore
	@ManyToMany
	private List<Cagnotte> cagnottes;
	
	@JsonIgnore
	@ManyToMany
	private List<Publication> publications ;
	

	

	@JsonIgnore
	@ManyToMany
	private List<Offer> offers;
	
	@JsonIgnore
	@OneToMany(mappedBy ="user" )
	private List<Training> trainings  ; 

	@JsonIgnore
	@OneToMany(mappedBy ="user" )
	private List<Comment> comments  ;
	@JsonIgnore
	@OneToMany(mappedBy ="user" )
	private List<Disponibilite> disponibilite ;
	
	
	
	
	@OneToMany(mappedBy ="user" )
	private List<Candidacy> Candidacies  ;

}