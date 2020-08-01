package org.anb.mii.security.config;


import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.anb.mii.model.User;
import org.anb.mii.security.common.model.CustomUserDetails;
import org.anb.mii.security.common.model.IRole;
import org.anb.mii.security.common.model.UserClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

// it defines what type of token will be constructed 
// what it will contain
@Component
@Slf4j
public class JwtTokenProvider {

	 /**
	   * THIS IS NOT A SECURE PRACTICE! For simplicity, we are storing a static key here. Ideally, in a
	   * microservices environment, this key would be kept on a config-server.
	   */
	  @Value("${security.jwt.token.secret-key:secret-key}")
	  private String secretKey;

	  @Value("${security.jwt.token.expire-length:3600000}")
	  private long validityInMilliseconds = 3600000; // 1h

	  @Autowired
	  private CustomUserDetails userDetails;

	  @PostConstruct
	  protected void init() {
	    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	  }

	  public String createToken(String username, User u) {

	    Claims claims = Jwts.claims().setSubject(username);
	   // claims.put("roles", u.getRoles().stream().map(s -> new SimpleGrantedAuthority(s.getAuthority())).filter(Objects::nonNull).collect(Collectors.toList()));
	    UserClaims uc= new UserClaims();
	    //uc.setCompanyName(u.getCompany().getName());uc.setEmail(u.getEmail());uc.setFirstName(u.getFirstName());uc.setLastName(u.getLastName());
	    IRole roles= new IRole();
	   // roles.setId(u.getRole().getId());
	  //  roles.setRoleName(u.getRole().getName()); 
	    StringBuilder priviliges=new StringBuilder();
	   // u.getRole().getPrivileges().forEach(e->priviliges.append(e.getName()).append(","));
	    roles.setPriviliges(priviliges.toString());
	    uc.setRole(roles);
	    claims.put("details",uc);
	    Date now = new Date();
	    Date validity = new Date(now.getTime() + validityInMilliseconds);

	    return Jwts.builder()//
	        .setClaims(claims)//
	        .setIssuedAt(now)//
	        .setExpiration(validity)//
	        .signWith(SignatureAlgorithm.HS256, secretKey)//
	        .compact();
	  }

	  public Authentication getAuthentication(String token) {
	    UserDetails userDetail = userDetails.loadUserByUsername(getUsername(token));
	    log.info("userDetails: "+userDetail);
	    Authentication auth=new UsernamePasswordAuthenticationToken(userDetails, "", userDetail.getAuthorities());
	    log.info("auth is: "+auth);
	    return auth;
	  }

	  public String getUsername(String token) {
	    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	  }

	  public String resolveToken(HttpServletRequest req) {
	    String bearerToken = req.getHeader("Authorization");
	  //  log.info("resolving Token");
	    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
	    	log.info(bearerToken);
	      return bearerToken.substring(7);
	    }
	    return null;
	  }

	  public boolean validateToken(String token) throws Exception {
	    try {
	    	log.info("validateToken seckret key is: "+secretKey);
	      Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
	      return true;
	    } catch (JwtException | IllegalArgumentException e) {
	      throw new Exception("Expired or invalid JWT token"+ HttpStatus.UNAUTHORIZED.toString());
	    }
	  }
}
