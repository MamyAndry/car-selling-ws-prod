package carselling.selling.controller;


import carselling.selling.repository.AnnouncementRepository;
import carselling.selling.response.ApiResponse;
import carselling.selling.entity.Announcement;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;





@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "announcement")
public class AnnouncementController
 {
	@Autowired
	private AnnouncementRepository repository;


	@PostMapping()
	public ResponseEntity<?> save(@RequestBody Announcement announcement){
		ApiResponse response = new ApiResponse();
		try{
			response.addData("data", repository.save(announcement));
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}
	}
	@PutMapping()
	public ResponseEntity<?> update(@RequestBody Announcement announcement){
		ApiResponse response = new ApiResponse();
		try{
			response.addData("data", repository.save(announcement));
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}
	}
	@DeleteMapping()
	public ResponseEntity<?> delete(@RequestBody Announcement announcement){
		ApiResponse response = new ApiResponse();
		try{
			repository.delete(announcement);
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

	@GetMapping("historique/{id}")
	public ResponseEntity<?> historiqueAnnoucement(@PathVariable String id){
		ApiResponse response = new ApiResponse();
		try{
			response.addData("data", repository.findUserHistorique(id));
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}
	}

	@GetMapping("status/{status}")
	public ResponseEntity<?> findNotValidated(@PathVariable int status){
		ApiResponse response = new ApiResponse();
		try{
			response.addData("data", repository.findByStatus(status));
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

}
