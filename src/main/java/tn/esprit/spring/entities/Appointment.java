package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Appointment implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idAppointment")
    private int id ; 
	private String reason ; 
	private Date dateAppointment;
	
	@ManyToOne
	@JsonIgnore
	private User userexpert ;
	
	@ManyToOne
	@JsonIgnore
	private User user ;
	
	@ManyToOne
	@JsonIgnore
	private User usera ;
	
	
	

}
