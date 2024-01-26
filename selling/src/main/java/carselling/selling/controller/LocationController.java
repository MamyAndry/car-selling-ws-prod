package carselling.selling.controller;


import carselling.selling.repository.LocationRepository;
import carselling.selling.response.ApiResponse;
import carselling.selling.entity.Location;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "location")
public class LocationController
 {

	@Autowired
	private LocationRepository repository;


	@PostMapping()
	public ResponseEntity<?> save(@RequestBody Location location){
		ApiResponse response = new ApiResponse();
		try{
			repository.delete(location);
			response.addData("data", "Inserted successfully");
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}	
	}
	@PutMapping()
	public ResponseEntity<?> update(@RequestBody Location location){
		ApiResponse response = new ApiResponse();
		try{
			repository.delete(location);
			response.addData("data", "Updated successfully");
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}	
	}
	@DeleteMapping()
	public ResponseEntity<?>  delete(@RequestBody Location location){
		ApiResponse response = new ApiResponse();
		try{
			repository.delete(location);
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
