package tn.esprit.spring.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
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
@Api
public class Report {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idReport")
    private long id; 
	private String subject; 
	private Date reportDate;
	private String status;
	@JsonIgnore
	@ManyToOne 
	private User user ; 

}
