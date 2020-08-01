package org.anb.mii.security.config;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

	private JwtTokenProvider jwtTokenProvider;

	  public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
	    this.jwtTokenProvider = jwtTokenProvider;
	  }

	  @Override
	  protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
		  String token = jwtTokenProvider.resolveToken(httpServletRequest);
	    try {
	      if (token != null && jwtTokenProvider.validateToken(token)) {
	    	  log.info("token is: "+token);
	        Authentication auth = jwtTokenProvider.getAuthentication(token);
	        SecurityContextHolder.getContext().setAuthentication(auth);
	      }
	    } catch (Exception ex) {
	      //this is very important, since it guarantees the user is not authenticated at all
	      SecurityContextHolder.clearContext();
	      httpServletResponse.sendError(403, ex.getMessage());
	      return;
	    }

	    filterChain.doFilter(httpServletRequest, httpServletResponse);
	  }
}
