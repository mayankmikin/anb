package org.anb.mii.controller;

import java.util.List;

import org.anb.mii.model.Library;
import org.anb.mii.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

// 1 class = 1 controller = 1 api
//1 method= 1 api operation
@RestController
@RequestMapping("/api/library")
@Slf4j	
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LibraryController {
	
	@Autowired
	private LibraryService libservice;

	@GetMapping("/getByName")
	public ResponseEntity<?> get(@RequestBody String libraryname)
	{
		
		return new ResponseEntity<Library> (libservice.findByName(libraryname), HttpStatus.OK);
	}
	
	@GetMapping("/getNearestName")
	public ResponseEntity<?> getNearestName(@RequestBody String libraryname)
	{
		
		return new ResponseEntity<List<Library>> (libservice.findNearestMatchingName(libraryname), HttpStatus.OK);
	}
	
}
