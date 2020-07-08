package org.anb.mii.controller;

import org.anb.mii.model.Student;
import org.anb.mii.repository.StudentRepository;
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
@RequestMapping("/api/student")
@Slf4j
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class StudentController {
	@Autowired
	StudentRepository studentRepo;
	
	//@GetMapping("/get")
	@RequestMapping(value = "/get",method = RequestMethod.GET)
	public Student get() {
		return new Student(new Long(2), "Himanshu", "8th", "C");
	}
	
	//@PostMapping("/create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Student> createStudentOnDb(@RequestBody Student studentToBeCreated){
		log.info("Request Body: {}",studentToBeCreated);
		
		studentToBeCreated=studentRepo.save(studentToBeCreated);
		log.info("Saved Body: {}",studentToBeCreated);
		
		return new ResponseEntity<Student>(studentToBeCreated,HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getStudentFromId(@PathVariable("id") Long id){
		if(studentRepo.findById(id).isPresent()) {
			return new ResponseEntity<Student>(studentRepo.findById(id).get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Data does not exist", HttpStatus.NOT_FOUND);
		}
	}
	
	
	

}
