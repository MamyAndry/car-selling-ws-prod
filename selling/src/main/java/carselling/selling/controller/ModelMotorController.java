package carselling.selling.controller;


import carselling.selling.repository.ModelMotorRepository;
import carselling.selling.response.ApiResponse;
import carselling.selling.entity.Model;
import carselling.selling.entity.ModelMotor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "modelMotor")
public class ModelMotorController
 {

	@Autowired
	private ModelMotorRepository repository;


	@PostMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> save(@RequestBody ModelMotor modelMotor){
		ApiResponse response = new ApiResponse();
		try{
			response.addData("data", repository.save(modelMotor));
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}
	}

	@PutMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> update(@RequestBody ModelMotor modelMotor){
		ApiResponse response = new ApiResponse();
		try{
			response.addData("data", repository.save(modelMotor));
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}
	}

	@DeleteMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> delete(@RequestBody ModelMotor modelMotor){
		ApiResponse response = new ApiResponse();
		try{
			repository.delete(modelMotor);
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
	public ResponseEntity<?> findById(@PathVariable int id){
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
	public ResponseEntity<?>  pagination(@PathVariable int debut, @PathVariable int fin) {
		ApiResponse response = new ApiResponse();
		try{
			response.addData("data", repository.paginer(debut, fin));
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}
	}

	@GetMapping("model")
	public ResponseEntity<?> getList(@RequestBody Model model){
		ApiResponse response = new ApiResponse();
		try{
			response.addData("data", repository.findByModel(model));
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}
	}

}
