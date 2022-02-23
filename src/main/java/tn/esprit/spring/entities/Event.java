package tn.esprit.spring.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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
public class Event {
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id ; 
	private Date date ; 
	@Enumerated(EnumType.STRING)
	private Type type ; 
	private String Description ; 
	private String Title ; 
	@ManyToOne
	@JsonIgnore
	private User host;
	
	@ManyToMany (mappedBy = "events")
	@JsonIgnore
	private List<User> users  ; 

}
