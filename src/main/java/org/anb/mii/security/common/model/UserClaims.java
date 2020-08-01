package org.anb.mii.security.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserClaims {

	private String firstName;

	private String lastName;

	private String email;
	
	private IRole role;
	
	private String  companyName;
}
