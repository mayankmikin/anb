package org.anb.mii.controller;


import org.anb.mii.model.Book;
import org.anb.mii.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/book")
@CrossOrigin(origins = "*",allowedHeaders = "*")

public class BookController {

	@Autowired
	private BookRepository bookRepo;
	@GetMapping("/get")
	public Book get() 
	{
		return new Book(new Long(3),"Philo","Rumi");
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") Long id)
	{
	
		return new ResponseEntity<Book> (new Book( new Long(5),"Phospo","mikin"),HttpStatus.ACCEPTED);
		
	}

	@PostMapping("/create")
	public ResponseEntity<Book> createBook(@RequestBody Book createdBook)
	{
		
		log.info("Book is creating: {}",createdBook);
		createdBook=bookRepo.save(createdBook);
		log.info("Created Book:{}",createdBook);
		
		return new ResponseEntity<Book> (createdBook,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/read/{id}")
	public ResponseEntity<?> readingBook(@PathVariable("id") Long id)
	{
		if(bookRepo.findById(id).isPresent()) 
		{
			return new ResponseEntity<Book> (bookRepo.findById(id).get(),HttpStatus.OK);
		
		}
		else 
		{
			return new ResponseEntity< String>("Id does not exist" +id,HttpStatus.NOT_FOUND );
		
		}
	
	}
	
}
