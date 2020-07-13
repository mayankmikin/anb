package org.anb.mii.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    
}
