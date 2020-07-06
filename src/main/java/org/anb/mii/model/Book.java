package org.anb.mii.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// java class= table
// java object = 1 row db
// java object properties= column of db
// oops real life entity in programatical world
@Entity
@Data
public class Book 
{
	//ctrl+shift+o
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id;
	 private String name;
	 private String authorName;
	
}
