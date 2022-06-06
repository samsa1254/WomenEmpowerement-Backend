package tn.esprit.spring.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

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
	

	public enum Type {
		Charity, Support_Group, Self_Help_Plan, Supportive_Psychotherapy
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_event")
	private Long Id;
	@NotNull //(message = "date shouldn't be empty")
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date dateDebut;
	@NotNull //(message = "date shouldn't be empty")
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date dateFin;
	
	@Enumerated(EnumType.STRING)
	private Type type;
	@NotNull //(message = "description shouldn't be empty")
	private String description;
	@NotNull //(message = "title shouldn't be empty")
    private String title;

	@OneToOne
	@JsonIgnore
	@Nullable
	private DBFile picture;
	
	@JsonIgnore
	@ManyToMany
	private List<User> participants;
	
	@OneToOne
	Cagnotte cagnotte;
	
}
