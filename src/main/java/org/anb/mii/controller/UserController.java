package org.anb.mii.controller;

import org.anb.mii.security.common.model.JWTTokenPayload;
import org.anb.mii.security.common.model.Login;
import org.anb.mii.service.UserService;
import org.anb.mii.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class UserController extends GenericController{

	@Autowired
	private JsonUtils mapper;
	@Autowired
	private ObjectMapper objectmapper;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody Login login) {
		try
		{
		return new ResponseEntity<JWTTokenPayload>(
				new JWTTokenPayload(userService.signin(login.getUsername(), login.getPassword())), HttpStatus.OK);
		}
		catch(Exception ex)
		{
			return  new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}
