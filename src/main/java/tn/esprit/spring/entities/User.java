package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
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

	@Column(nullable = true)
	private Date subscribtion ; 

	
	@Column(nullable = true)
	private String adminsector ; 
	
	
	@Column(nullable = true)
	private String tutorspeciality ; 
	
	
	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
	private TutorType tutortype; 
	
	
	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
	private ExpertSpec expertspeciality; 
	
	@Column(nullable = true)
	private String expertadress ; 
	
	@Column(nullable = true)
	private long expertnumber ; 
	
	
	
	
	@OneToMany(mappedBy ="user" )
	private List<Ad> ads  ; 
	
	@OneToMany(mappedBy ="user" )
	private List<Message> messages  ; 
	
	@OneToMany(mappedBy ="user" )
	private List<Reaction> reactions  ; 
	
	@OneToMany(mappedBy ="user" )
	private List<Appointment> appointments  ; 
	
	@OneToMany(mappedBy ="user" )
	private List<Comment> comments  ; 
	
	@OneToMany(mappedBy ="user" )
	private List<Favorites> favorites  ; 
	
	@OneToMany (mappedBy = "user")
	private List<Report> reports ;
	
	@ManyToMany
	private List<Event> events;
	
	@ManyToMany
	private List<Cagnotte> cagnottes;
	
	@ManyToMany
	private List<Publication> publications ;
	
	@OneToMany(mappedBy = "host")
	private List<Event> events2 ;
	

	
	@ManyToMany
	private List<Offer> offers;
	
	


}