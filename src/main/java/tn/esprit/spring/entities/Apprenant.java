package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Setter;
import lombok.ToString;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
 @Getter
 @Setter
 @NoArgsConstructor
 @AllArgsConstructor
 @ToString
 @EqualsAndHashCode
 @Api
public class Apprenant implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idApprenant ; 
	private String Nom ;
	private String Prenom ;
	private Integer telephone ;
	private String Email ;
	@ManyToMany(mappedBy = "apprenants")
	private List<Formation> formations;
}
