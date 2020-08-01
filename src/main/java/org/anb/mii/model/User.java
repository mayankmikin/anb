package org.anb.mii.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String fname;
	private String lname;
	private String fathname;
	private String mothname;
	private String mobile;
	private String classname;
	private String section;
	private String gender;
	@NotNull
	@Column(unique = true)
	private String email;
	@Column
	private String password;



//	@ManyToMany
//    @JoinTable( 
//        name = "user_roles", 
//        joinColumns = @JoinColumn(
//          name = "user_id", referencedColumnName = "userId"), 
//        inverseJoinColumns = @JoinColumn(
//          name = "role_id", referencedColumnName = "id")) 
//    private Collection<Role> roles;
	 

}
