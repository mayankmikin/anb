package org.anb.mii.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.anb.mii.model.Book;
import org.anb.mii.model.Student;
import org.anb.mii.repository.BookRepository;
import org.anb.mii.repository.StudentRepository;
import org.anb.mii.requestmodels.BookLikedByStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/student")
@Slf4j	
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudentController {
	
	@Autowired
	private StudentRepository stuRepo;
	
	@Autowired
	private BookRepository  bookRepo;
	
	@PatchMapping("/like")
	public ResponseEntity<String> like(@RequestBody BookLikedByStudent bookLikedByStudent)
	{
		log.info("request body :{}",bookLikedByStudent);
		if(stuRepo.findById(bookLikedByStudent.getStudentId()).isPresent())
		{
			if(bookRepo.findAllById(bookLikedByStudent.getBooks())!=null)
			{
				Student s=stuRepo.findById(bookLikedByStudent.getStudentId()).get();
//				Set<Book> booksliked=
//						StreamSupport.stream(
//						bookRepo.findAllById(bookLikedByStudent.getBooks()).spliterator()
//						,false)
//						.collect(Collectors.toSet());
				Set<Book> booksliked= new HashSet<>();
				bookRepo.findAllById(bookLikedByStudent.getBooks())
				.iterator()
				.forEachRemaining(b->{
					booksliked.add(b);
				});
				s.setLikedBooks(booksliked);
				stuRepo.save(s);
			}
			
		}
		return new ResponseEntity<String> ("done", HttpStatus.ACCEPTED);
	}

}
