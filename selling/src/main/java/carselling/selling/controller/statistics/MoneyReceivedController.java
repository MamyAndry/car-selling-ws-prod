package carselling.selling.controller.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import carselling.selling.repository.statistics.MoneyPerceivedRepository;
import carselling.selling.response.ApiResponse;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "profitStatistics")
public class MoneyReceivedController {
    @Autowired
	private MoneyPerceivedRepository repository;

    // @GetMapping("profit/{id}")
	// public ResponseEntity<?> getProfitPerMonthPerYear(@PathVariable String id){
	//  	return ResponseEntity.ok(repository.getProfitPerMonthPerYear(id));
	// }

    @GetMapping("profit/{id}")
	public ResponseEntity<?> getProfitPerYear(@PathVariable String id){
		ApiResponse response = new ApiResponse();
		try{
			response.addData("data", repository.getProfitPerYear(id));
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}
	}
	@GetMapping("profit/{id}/{year}")
	public ResponseEntity<?> getProfitOfYear(@PathVariable String id, @PathVariable int year){
		ApiResponse response = new ApiResponse();
		try{
			response.addData("data", repository.getProfitOfYear(id, year));
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}
	}

    @GetMapping("funds")
	public ResponseEntity<?> getFundVariationPerMonthPerYear(){
		ApiResponse response = new ApiResponse();
		try{
			response.addData("data", repository.getFundVariationPerMonthPerYear());
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}
	}

    @GetMapping("fund")
	public ResponseEntity<?> getFundVariationPerYears(){
		ApiResponse response = new ApiResponse();
		try{
			response.addData("data", repository.getFundVariationPerYears());
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}
   }

	@GetMapping("fund/{year}")
	public ResponseEntity<?> getFundVariationForYear(@PathVariable int year){
		ApiResponse response = new ApiResponse();
		try{
			response.addData("data", repository.getFundVariationForYear(year));
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}
	}	

}
