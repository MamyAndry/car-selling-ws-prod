package carselling.selling.controller;


import carselling.selling.repository.VenteRepository;
import carselling.selling.response.ApiResponse;
import carselling.selling.service.vente.VenteService;
import carselling.selling.entity.Vente;
import org.springframework.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "vente")
public class VenteController
 {

	@Autowired
	private VenteRepository repository;
	@Autowired 
	private VenteService venteService;

	@PostMapping()
	public ResponseEntity<?> save(@RequestBody Vente vente){
		ApiResponse response = new ApiResponse();
		try{
			repository.save(vente);
			response.addData("data", "Inserted successfully");
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}
	}
	@PutMapping()
	public ResponseEntity<?> update(@RequestBody Vente vente){
		ApiResponse response = new ApiResponse();
		try{
			repository.save(vente);
			response.addData("data", "Updated successfully");
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}
	}
	@DeleteMapping()
	public ResponseEntity<?> delete(@RequestBody Vente vente){
		ApiResponse response = new ApiResponse();
		try{
			repository.delete(vente);
			response.addData("data", "Deleted successfully");
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}
	}
	@GetMapping()
	public ResponseEntity<?> findAll(){
		ApiResponse response = new ApiResponse();
		try{
			response.addData("data", repository.findAll());
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}
	}

	@PostMapping("validateSell")
	public ResponseEntity<?> sellCar(@RequestBody Vente vente) {
		try {
			return venteService.sellCar(vente);
		} catch (Exception e) {
			return ResponseEntity.ok(e.getMessage());
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable String id){
		ApiResponse response = new ApiResponse();
		try{
			response.addData("data", repository.findById(id));
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}
	}


	@GetMapping("{debut}/{fin}")
	public ResponseEntity<?>  getMethodName(@PathVariable int debut, @PathVariable int fin) {
		ApiResponse response = new ApiResponse();
		try{
			response.addData("data", repository.paginer(debut, fin));
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}
	}
}
