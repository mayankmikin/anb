package org.anb.mii.controller;

import org.anb.mii.security.config.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GenericController {

    @Autowired
    public IAuthenticationFacade authenticationFacade;

    public String getEmailFromToken() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }
}
