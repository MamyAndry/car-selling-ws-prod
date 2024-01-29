package carselling.selling.controller.messagerie;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import carselling.selling.entity.messagerie.Message;
import carselling.selling.repository.messagerie.MessageRepository;
import carselling.selling.response.ApiResponse;
import carselling.selling.service.messagerie.MessageService;	

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "messages")
public class MessageController {
    @Autowired
	private MessageRepository repository;
    @Autowired
    MongoTemplate mongoTemplate;
	@Autowired
	MessageService service;


	@PostMapping()
	public ResponseEntity<?> save(@RequestBody Message message){
		ApiResponse response = new ApiResponse();
        try {
			repository.save(message);
			response.addData("data", "Inserted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
			response.addError("error", e.getCause().getMessage());
            return ResponseEntity.ok(response);
        }
	}
	@PutMapping()
	public ResponseEntity<?> update(@RequestBody Message message){
		ApiResponse response = new ApiResponse();
        try {
			repository.save(message);
			response.addData("data", "Updated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
			response.addError("error", e.getCause().getMessage());
            return ResponseEntity.ok(response);
        }
	}
	@DeleteMapping()
	public ResponseEntity<?> delete(@RequestBody Message message){
		ApiResponse response = new ApiResponse();
        try {
			repository.delete(message);
			response.addData("data", "Message deleted");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
			response.addError("error", e.getCause().getMessage());
            return ResponseEntity.ok(response);
        }
	}
	@GetMapping()
	public ResponseEntity<?> findAll(){
		ApiResponse response = new ApiResponse();
        try {
			response.addData("data", mongoTemplate.findAll(Message.class));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
			response.addError("error", e.getCause().getMessage());
            return ResponseEntity.ok(response);
        }
	}

	@GetMapping("{id}/{id2}")
	public ResponseEntity<?> getMessages(@PathVariable String id, @PathVariable String id2) {
		ApiResponse response = new ApiResponse();
        try {
			response.addData("data", service.getMessagesBetweenUsers(id, id2));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
			response.addError("error", e.getCause().getMessage());
            return ResponseEntity.ok(response);
        }
	}
	

	@GetMapping("conversation/{id}")
	public ResponseEntity<?> getConversation(@PathVariable String id) {
		ApiResponse response = new ApiResponse();
        try {
			response.addData("data", service.getConversations(id));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
			response.addError("error", e.getCause().getMessage());
            return ResponseEntity.ok(response);
        }	}
	

}
