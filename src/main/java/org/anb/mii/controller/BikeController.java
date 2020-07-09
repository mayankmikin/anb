package org.anb.mii.controller;

import org.anb.mii.model.Bike;
import org.anb.mii.repository.BikeRepository;
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
@RequestMapping("/api/bike")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class BikeController {
	@Autowired
	private BikeRepository bikeRepo;
	
	@GetMapping("/get")
	public Bike get() {
		
		return new Bike(new Long(4),"pulsar",2000);
		
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Bike> gettingBike (@PathVariable("id") Long id ){
		
		return new ResponseEntity<Bike> (new Bike(new Long(4),"apache",1988),HttpStatus.ACCEPTED);
	}
	@PostMapping("/create")
	public ResponseEntity<Bike> creatingBike (@RequestBody Bike bikeCreated){
		log.info("Bike is Creating:{}",bikeCreated);
		bikeCreated=bikeRepo.save(bikeCreated);
		log.info("Bike is Created with Id:{}",bikeCreated);
		
		return new ResponseEntity<Bike>(bikeCreated,HttpStatus.ACCEPTED );
		
		
	}
	
	@GetMapping("/read/{id}")
	public ResponseEntity<?> readingBike (@PathVariable("id") Long id){
		if(bikeRepo.findById(id).isPresent()) {
			return new ResponseEntity<Bike> (bikeRepo.findById(id).get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Does not exit " +id ,HttpStatus.NOT_FOUND);
		}
	}
}
