package org.anb.mii.controller;

import org.anb.mii.model.Student;
import org.anb.mii.repository.StudentRepository;
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
@RequestMapping("/api/student")
@CrossOrigin(origins = "*",allowedHeaders = "*")

public class StudentController {
	
	@Autowired
	private StudentRepository studentRepo;
	@GetMapping("/get")
	public Student get() {
		
		return new Student(new Long(5),"Himanshu","10");
				
	}
	
	@PostMapping("/create")
	public ResponseEntity<Student> creatingStudent(@RequestBody Student createStudent){
		log.info("creating Student : {}",createStudent);
		createStudent= studentRepo.save(createStudent);
		
		log.info("Student Created :{}",createStudent);
		
		return new ResponseEntity<Student> (createStudent,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Student> gettingStudent(@PathVariable("id") Long id){
		
		return new ResponseEntity<Student> (new Student(new Long(10),"Himani","1"),HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/read/{id}")
	public ResponseEntity<?> readingStudent(@PathVariable("id") Long id){
		if(studentRepo.findById(id).isPresent()) {
			
			return new ResponseEntity<Student>(studentRepo.findById(id).get(),HttpStatus.OK);
		}
		
		else {
			return new ResponseEntity<String>("This id doesnot exist"+ id,HttpStatus.NOT_FOUND);
		}
	}
	
	
	
}
