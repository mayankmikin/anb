package org.anb.mii.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@ManyToMany
	@JoinTable(
			  name = "books_like", 
			  joinColumns = @JoinColumn(name = "student_id",referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "book_id",referencedColumnName = "id"))
    Set<Book> likedBooks;

}
