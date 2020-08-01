package org.anb.mii.security.common.model;

import org.anb.mii.model.User;
import org.anb.mii.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetails implements UserDetailsService {

	@Autowired
	  private UserRepository userRepository;

	  @Override
	  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	     User u = userRepository.findByEmail(email);
	   
//	    if (u == null) {
//	    	u=userRepository.findByUserName(email);
//		    }
	    
	    if (u == null) {
	      throw new UsernameNotFoundException("User '" + email + "' not found");
	    }
	    
	    UserClaims user= new UserClaims();
	    //user.setCompanyName(u.getCompany().getName());user.setEmail(u.getEmail());user.setFirstName(u.getFirstName());user.setLastName(u.getLastName());
	    IRole roles= new IRole();
	    //roles.setId(u.getRole().getId());
	    //roles.setRoleName(u.getRole().getName()); 
	    StringBuilder priviliges=new StringBuilder();
	    //u.getRole().getPrivileges().forEach(e->priviliges.append(e.getName()).append(","));
	    roles.setPriviliges(priviliges.toString());
	    user.setRole(roles);
	    
	    return org.springframework.security.core.userdetails.User//
	        .withUsername(email)//
	        .password(u.getPassword())//
	        .authorities(user.getRole())//
	        .accountExpired(false)//
	        .accountLocked(false)//
	        .credentialsExpired(false)//
	        .disabled(false)//
	        .build();
	  }

}
