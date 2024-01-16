package carselling.selling.controller;


import carselling.selling.repository.GearBoxRepository;
import carselling.selling.service.Service;
import carselling.selling.entity.GearBox;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "gearBox")
public class GearBoxController
 {

	@Autowired
	private GearBoxRepository repository;


	@PostMapping()
	public ResponseEntity<GearBox> save(@RequestBody GearBox gearBox){
		gearBox.setIdGearBox(Service.getPK("GEAR", repository.getNextSequenceValue(), 8));
	 	return ResponseEntity.ok(repository.save(gearBox));
	}
	@PutMapping()
	public ResponseEntity<GearBox> update(@RequestBody GearBox gearBox){
	 	return ResponseEntity.ok(repository.save(gearBox));
	}
	@DeleteMapping()
	public void delete(@RequestBody GearBox gearBox){
	 	repository.delete(gearBox);
	}
	@GetMapping()
	public ResponseEntity<Iterable<GearBox>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
