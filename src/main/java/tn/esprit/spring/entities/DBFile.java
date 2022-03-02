package tn.esprit.spring.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;


import io.swagger.annotations.Api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Api
public class DBFile {
	@Id
	@Column(length = 500)
	private String id ; 
	private String name ; 
	private String type ; 
	@Lob
	private byte[] data ; 
	
	@ManyToOne
	@JsonIgnore
	@Nullable
	private Publication publication ;
	
	public DBFile( String id , String name , String type , byte[] data )
	{
		this.id = id ; 
		this.name = name ; 
		this.type = type ; 
		this.data =data ;
	}
}
