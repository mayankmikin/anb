package org.anb.mii.controller;

import org.anb.mii.model.Book;
import org.anb.mii.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/read/{id}")
	public ResponseEntity<?> readFromDb(@PathVariable("id") Long id)
	{
		// wrong
		//return new ResponseEntity<Book> (bookRepo.findById(id).get(), HttpStatus.ACCEPTED);
		// old way for null check
//		if(bookRepo.findById(id).get() != null)
//		{
//			
//		}
		
		// java 8 null check
		if(bookRepo.findById(id).isPresent())
		{
			return new ResponseEntity<Book> (bookRepo.findById(id).get(), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String> ("book does not exists by id :"+id, HttpStatus.OK) ;
		}
		
		
	}
	
	@PatchMapping("/update/bookname/{id}")
	public ResponseEntity<?> create(@PathVariable("id") Long id,@RequestBody String booknametobeupdated)
	{
		log.info("request body :{}",booknametobeupdated);
		// java 8 null check
				if(bookRepo.findById(id).isPresent())
				{
					Book bookTobeUpdated=bookRepo.findById(id).get();
					bookTobeUpdated.setName(booknametobeupdated);
					bookRepo.save(bookTobeUpdated);
					return new ResponseEntity<Book> (bookTobeUpdated, HttpStatus.OK);
				}
				else
				{
					return new ResponseEntity<String> ("book does not exists by id :"+id, HttpStatus.OK) ;
				}
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Book> update(@PathVariable("id") Long id,@RequestBody Book request)
	{
		log.info("request body :{}",request);
		Book bookFromDb=bookRepo.findById(id).get();
		bookFromDb.setAuthorName(request.getAuthorName());
		bookFromDb.setName(request.getName());
		bookFromDb=bookRepo.save(bookFromDb);
		log.info("after saving  :{}",bookFromDb);
		return new ResponseEntity<Book> (bookFromDb, HttpStatus.ACCEPTED);
	}
	
	
}
