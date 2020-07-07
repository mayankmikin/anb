package org.anb.mii.controller;

import org.anb.mii.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@GetMapping("/get")
	public Book get()
	{
		return new Book(new Long(4),"da vinci code","dan brown");
		//return new ResponseEntity<List<BLETag>> (bleTags, HttpStatus.OK);
	}

	//ResponseEntity
	@GetMapping("/get/{id}")
	public ResponseEntity<Book> getProdLevel(@PathVariable("id") Long id)
	{
		//return new Book(new Long(4),"da vinci code","dan brown");
		Book b=new Book(new Long(4),"da vinci code","dan brown");
		return new ResponseEntity<Book> (b, HttpStatus.ACCEPTED);
	}
	
}
