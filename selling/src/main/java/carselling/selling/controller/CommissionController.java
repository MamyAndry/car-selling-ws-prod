package carselling.selling.controller;


import carselling.selling.repository.CommissionRepository;
import carselling.selling.response.ApiResponse;
import carselling.selling.entity.Commission;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "commission")
public class CommissionController
 {

	@Autowired
	private CommissionRepository repository;


	@PostMapping()
	public ResponseEntity<?> save(@RequestBody Commission commission){
		ApiResponse response = new ApiResponse();
		try{
			repository.save(commission);
			response.addData("data", "Inserted successfully");
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}
	}
	@PutMapping()
	public ResponseEntity<?> update(@RequestBody Commission commission){
		ApiResponse response = new ApiResponse();
		try{
			repository.save(commission);
			response.addData("data", "Updated successfully");
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}
	}
	@DeleteMapping()
		public ResponseEntity<?> delete(@RequestBody Commission commission){
		ApiResponse response = new ApiResponse();
		try{
			repository.delete(commission);
			response.addData("data", "Deleted successfully");
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}
	}
	@GetMapping()
	public ResponseEntity<Iterable<Commission>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
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
