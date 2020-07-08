package org.anb.mii.controller;


import org.anb.mii.model.Book;
import org.anb.mii.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/book")
@Slf4j
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class BookController {
	@Autowired
	BookRepository bookRepo;
	
	@GetMapping("/get")
	public Book get() {
		return new Book(new Long(4),"Da Vinci Code","Dan Brown");
	}

	
	@RequestMapping(value= "/read/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> read(@PathVariable("id") Long id){
		if(bookRepo.findById(id).isPresent()) {
			return new ResponseEntity<Book>(bookRepo.findById(id).get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("No data for given id", HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(value = "/create",method = RequestMethod.POST)
	public ResponseEntity<Book> create(@RequestBody Book bookToBeCreated){
		log.info("request body : {}",bookToBeCreated);
		bookToBeCreated = bookRepo.save(bookToBeCreated);
		log.info("saved body :{}",bookToBeCreated);
		return new ResponseEntity<Book>(bookToBeCreated,HttpStatus.CREATED);
		
	}

}
