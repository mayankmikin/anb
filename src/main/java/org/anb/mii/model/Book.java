package org.anb.mii.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id;
	 private String name;
	 private String authorName;
	 
//	 @ManyToOne
//     @JoinColumn(name="library_id", nullable=false)
//     private Library library;
	
	 @ManyToMany(mappedBy = "books")
	 private List<Library> library;
}
