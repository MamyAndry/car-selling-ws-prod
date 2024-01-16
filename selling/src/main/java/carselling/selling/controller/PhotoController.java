package carselling.selling.controller;


import carselling.selling.repository.PhotoRepository;
import carselling.selling.entity.Photo;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "photo")
public class PhotoController
 {

	@Autowired
	private PhotoRepository repository;


	@PostMapping()
	public ResponseEntity<Photo> save(@RequestBody Photo photo){
	 	return ResponseEntity.ok(repository.save(photo));
	}
	@PutMapping()
	public ResponseEntity<Photo> update(@RequestBody Photo photo){
	 	return ResponseEntity.ok(repository.save(photo));
	}
	@DeleteMapping()
	public void delete(@RequestBody Photo photo){
	 	repository.delete(photo);
	}
	@GetMapping()
	public ResponseEntity<Iterable<Photo>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
