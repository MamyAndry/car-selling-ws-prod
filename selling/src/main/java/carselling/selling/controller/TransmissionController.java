package carselling.selling.controller;


import carselling.selling.repository.TransmissionRepository;
import carselling.selling.entity.Transmission;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "transmission")
public class TransmissionController
 {

	@Autowired
	private TransmissionRepository repository;


	@PostMapping()
	public ResponseEntity<Transmission> save(@RequestBody Transmission transmission){
	 	return ResponseEntity.ok(repository.save(transmission));
	}
	@PutMapping()
	public ResponseEntity<Transmission> update(@RequestBody Transmission transmission){
	 	return ResponseEntity.ok(repository.save(transmission));
	}
	@DeleteMapping()
	public void delete(@RequestBody Transmission transmission){
	 	repository.delete(transmission);
	}
	@GetMapping()
	public ResponseEntity<Iterable<Transmission>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
