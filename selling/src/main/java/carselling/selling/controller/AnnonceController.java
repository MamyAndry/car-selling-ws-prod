package carselling.selling.controller;


import carselling.selling.repository.AnnonceRepository;
import carselling.selling.response.ApiResponse;
import carselling.selling.service.Service;
import carselling.selling.entity.Annonce;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "annonce")
public class AnnonceController
 {

	@Autowired
	private AnnonceRepository repository;


	@PostMapping()
	public ResponseEntity<?> save(@RequestBody Annonce annonce){
		ApiResponse response = new ApiResponse();
		try{
			annonce.setIdAnnonce(Service.getPK("ANN", repository.getNextSequenceValue(), 7));
			repository.save(annonce);
			response.addData("data", "Inserted successfully");
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);	
		}
	}
	@PutMapping()
	public ResponseEntity<?> update(@RequestBody Annonce annonce){
		ApiResponse response = new ApiResponse();
		try{
			repository.save(annonce);
			response.addData("data", "Updated succsessfully");
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}
	}
	@DeleteMapping()
	public ResponseEntity<?> delete(@RequestBody Annonce annonce){
		ApiResponse response = new ApiResponse();
		try{
			 repository.delete(annonce);
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





}
