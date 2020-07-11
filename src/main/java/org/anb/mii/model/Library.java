package org.anb.mii.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Library {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    //@Column(name = "library_name")
    private String name;
 
//    @OneToOne
//    @JoinColumn(name = "address_id")
//    @RestResource(path = "libraryAddress", rel="address")
//    private Address address;
}
