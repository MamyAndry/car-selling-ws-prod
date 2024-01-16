package carselling.selling.controller;


import carselling.selling.repository.FavorisRepository;
import carselling.selling.entity.Favoris;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "favoris")
public class FavorisController
 {

	@Autowired
	private FavorisRepository repository;


	@PostMapping()
	public ResponseEntity<Favoris> save(@RequestBody Favoris favoris){
	 	return ResponseEntity.ok(repository.save(favoris));
	}
	@PutMapping()
	public ResponseEntity<Favoris> update(@RequestBody Favoris favoris){
	 	return ResponseEntity.ok(repository.save(favoris));
	}
	@DeleteMapping()
	public void delete(@RequestBody Favoris favoris){
	 	repository.delete(favoris);
	}
	@GetMapping()
	public ResponseEntity<Iterable<Favoris>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
