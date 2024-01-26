package carselling.selling.controller;


import carselling.selling.repository.ProfitRepository;
import carselling.selling.response.ApiResponse;
import carselling.selling.entity.Profit;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "profit")
public class ProfitController
 {

	@Autowired
	private ProfitRepository repository;


	@PostMapping()
	public ResponseEntity<?> save(@RequestBody Profit profit){
		ApiResponse response = new ApiResponse();
		try{
			repository.save(profit);
			response.addData("data", "Inserted successfully");
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}
	}
	@PutMapping()
	public ResponseEntity<?> update(@RequestBody Profit profit){
		ApiResponse response = new ApiResponse();
		try{
			repository.save(profit);
			response.addData("data", "Updated successfully");
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}	}
	@DeleteMapping()
	public ResponseEntity<?> delete(@RequestBody Profit profit){
		ApiResponse response = new ApiResponse();
		try{
			repository.delete(profit);
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
