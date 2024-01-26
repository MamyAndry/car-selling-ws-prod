package carselling.selling.controller;


import carselling.selling.repository.OriginRepository;
import carselling.selling.response.ApiResponse;
import carselling.selling.entity.Origin;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "origin")
public class OriginController
 {

	@Autowired
	private OriginRepository repository;


	@PostMapping()
	public ResponseEntity<?> save(@RequestBody Origin origin){
		ApiResponse response = new ApiResponse();
		try{
			repository.delete(origin);
			response.addData("data", "Inserted successfully");
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}	
	}
	@PutMapping()
	public ResponseEntity<?> update(@RequestBody Origin origin){
		ApiResponse response = new ApiResponse();
		try{
			repository.delete(origin);
			response.addData("data", "Updated successfully");
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}	
	}
	@DeleteMapping()
	public ResponseEntity<?> delete(@RequestBody Origin origin){
		ApiResponse response = new ApiResponse();
		try{
			repository.delete(origin);
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
