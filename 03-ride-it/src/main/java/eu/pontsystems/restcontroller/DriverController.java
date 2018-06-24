package eu.pontsystems.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.pontsystems.controller.LoginController;
import eu.pontsystems.entity.Car;
import eu.pontsystems.entity.User;
import eu.pontsystems.service.DriverService;
import eu.pontsystems.service.UserService;

@RestController
public class DriverController {
	
	@Autowired
	private DriverService driverService;
	
	@GetMapping(path="/allCars",produces = "application/json")
	public ResponseEntity<?> getAllCars() {
		Map<Integer, Object> allCars = driverService.getAllCars(); 
		return new ResponseEntity<>(allCars, HttpStatus.OK);
	}
	
	
	@PostMapping(path="/cars",consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> registerNewCar(@RequestBody User newCar) {
		System.out.println(newCar);
		System.out.println("NEW CAR DATA: ");
		driverService.registerNewCar(newCar);
		return new ResponseEntity<>("car registered!",HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/cars/{carId}/users/{userId}",consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> removeDriverFromCar(@PathVariable(value="carId") Integer carId, @PathVariable(value="userId") Integer userId) {
		driverService.removeFromCar(carId, userId);
		return new ResponseEntity<>("Successfully removed!!!!", HttpStatus.OK);
	}
	
	@PutMapping(path="/cars/{carId}/users/{userId}",consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> driverApply(@PathVariable(value="carId") Integer carId, @PathVariable(value="userId") Integer userId, @RequestBody User existingCar) {
		boolean isApplied = driverService.applyDriver(carId, userId, existingCar);
		if(isApplied) {
			return new ResponseEntity<>("Successfully applied!!!!", HttpStatus.OK);	
		}else {
			return new ResponseEntity<>("You already have a car applied to, please remove your application and after you can apply to an existing car!", HttpStatus.OK);
		}	
	}
	
}
