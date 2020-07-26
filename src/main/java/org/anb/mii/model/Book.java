package org.anb.mii.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// java class= table
// java object = 1 row db
// java object properties= column of db
// oops real life entity in programatical world
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book 
{
	//ctrl+shift+o
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)// not for production
	 private Long id;
	 private String name;
	 private String authorName;
	 @ManyToOne
     @JoinColumn(name="library_id", nullable=false)
     private Library library;
	 @ManyToMany(mappedBy = "likedBooks")
	 Set<Student> likes;
}
