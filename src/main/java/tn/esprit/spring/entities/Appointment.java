package tn.esprit.spring.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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

public class Appointment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idAppointment")
    private long id ; 
	private String reason ; 
	private Date dateAppointment;
	
	@ManyToOne
	private User user ; 
	
	

}
