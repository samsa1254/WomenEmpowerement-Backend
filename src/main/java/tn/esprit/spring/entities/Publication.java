package tn.esprit.spring.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

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
public class Publication {
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idPublication;
	private String post ; 
	@CreationTimestamp
	private LocalDate date ; 
	@CreationTimestamp
	private LocalTime time ;
	private String state ; 
	
	@ManyToOne
	//@JsonIgnore
	private User user ; 
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy =  "publication")
	@JsonIgnore
	private List<Reaction> reactions ; 
	
	@OneToMany (cascade = CascadeType.ALL , mappedBy =  "publication")
	@JsonIgnore
	private List<CommentD> commentDs;

	@Override
	public String toString() {
		return "Publication [idPublication=" + idPublication + ", post=" + post + ", date=" + date + ", time=" + time
				+ ", state=" + state + ", user=" + user + ", reactions=" + reactions + ", commentDs=" + commentDs + "]";
	}
	

	
	
	

}
