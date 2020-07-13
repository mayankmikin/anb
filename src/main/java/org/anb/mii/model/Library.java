package org.anb.mii.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// owner entity
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Library {
	// json = java (spring)
	// java = db (hibernate JPA)
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    //@Column(name = "library_name")
    private String name;
    // owned entity
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    
    // one to many
    // library may exists when a new book is added
    @OneToMany(mappedBy="library")
    private Set<Book> books;
    
}
