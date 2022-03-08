package tn.esprit.spring.entities;





import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


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
public class Reaction {
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id ; 
	@Enumerated(EnumType.STRING)
	private ReactionValue value ; 
	
	
	
	@ManyToOne
	private Publication publication ; 
	

}
