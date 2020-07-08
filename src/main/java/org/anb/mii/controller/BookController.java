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

// 1 class = 1 controller = 1 api
//1 method= 1 api operation
@RestController
@RequestMapping("/api/book")
@Slf4j	
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookController {
	
	@Autowired
	private BookRepository bookRepo;
	
	@GetMapping("/get")
	public Book get()
	{
		// created object and returned
		return new Book(new Long(4),"da vinci code","dan brown");
		//return new ResponseEntity<List<BLETag>> (bleTags, HttpStatus.OK);
	}

	//ResponseEntity
	@GetMapping("/get/{id}")
	public ResponseEntity<Book> getProdLevel(@PathVariable("id") Long id)
	{
		// created object and returned with HTTP status code
		//return new Book(new Long(4),"da vinci code","dan brown");
		Book b=new Book(new Long(4),"da vinci code","dan brown");
		return new ResponseEntity<Book> (b, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Book> create(@RequestBody Book bookToBeCreated)
	{
		log.info("request body :{}",bookToBeCreated);
		bookToBeCreated=bookRepo.save(bookToBeCreated);
		log.info("after saving  :{}",bookToBeCreated);
		return new ResponseEntity<Book> (bookToBeCreated, HttpStatus.ACCEPTED);
	}
	
	
}
