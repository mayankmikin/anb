package org.anb.mii.security.common.model;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties({"authority"})
public class IRole implements Serializable,GrantedAuthority{

	private Long id;
	private String roleName;
	private String priviliges;
	
	   public String getAuthority() {
	        return priviliges;
	      }
}
