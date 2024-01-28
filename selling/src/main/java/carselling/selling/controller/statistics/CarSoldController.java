package carselling.selling.controller.statistics;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import carselling.selling.repository.statistics.CarSoldRepository;
import carselling.selling.response.ApiResponse;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "saleStatistics")
public class CarSoldController {
	@Autowired
	private CarSoldRepository repository;

	@GetMapping("sold")
	public ResponseEntity<?> getMostSoldCarPerYear(){
		ApiResponse response = new ApiResponse();
		try{
			response.addData("data", repository.getMostSoldCar());
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}
	}

	@GetMapping("sold/{year}")
	public ResponseEntity<?> getMostSoldCarForYear(@PathVariable int year){
		ApiResponse response = new ApiResponse();
		try{
			response.addData("data", repository.getSaleStatsForYear(year));
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}
	}

    @GetMapping("brand")
	public ResponseEntity<?> getMostSoldBrandPerYear(){
		ApiResponse response = new ApiResponse();
		try{
			response.addData("data", repository.getMostSoldBrand());
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}
	}

    @GetMapping("brand/{year}")
	public ResponseEntity<?> getMostSoldBrandForYear(@PathVariable int year){
		ApiResponse response = new ApiResponse();
		try{
			response.addData("data", repository.getMostSoldBrandForYear(year));
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}
   }

	@GetMapping("model/{year}/{id}")
	public ResponseEntity<?> getMostSoldModelPerMonthForYear(@PathVariable int year, @PathVariable String id){
		ApiResponse response = new ApiResponse();
		try{
			response.addData("data", repository.getSaleStatsOfModelForYear(year, id));
			return ResponseEntity.ok(response);
		}catch(Exception e){
			response.addError("error", e.getCause().getMessage());
			return ResponseEntity.ok(response);
		}
	}	
}
