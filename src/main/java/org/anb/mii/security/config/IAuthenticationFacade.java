package org.anb.mii.security.config;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
	 Authentication getAuthentication();
}
