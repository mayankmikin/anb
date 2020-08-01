package org.anb.mii.service;

import org.anb.mii.model.User;
import org.anb.mii.repository.UserRepository;
import org.anb.mii.security.config.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager customAuthenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    public String signin(String email, String password) throws Exception {
        try {
            //password=passwordEncoder.encode(password);
            customAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            User u = repository.findByEmail(email);
//            if (u == null) {
//                u = repository.findByUserName(email);
//            }
            u.setPassword("");
            return jwtTokenProvider.createToken(email, u);
        } catch (AuthenticationException e) {
            log.error("Invalid username/password supplied");
        	throw new Exception("Invalid username/password supplied"+ HttpStatus.FORBIDDEN);
        }
    }
}
