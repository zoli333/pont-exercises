package eu.pontsystems.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import eu.pontsystems.entity.Car;
import eu.pontsystems.entity.RegistrationData;
import eu.pontsystems.entity.User;
import eu.pontsystems.service.RegistrationService;

@RestController
public class RestRegistrationController {
	
	@Autowired
	RegistrationService regService;
	
	
	@PostMapping(path = "/register/registernewuser", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> registerUser(@RequestBody RegistrationData regData) {
		Car car = new Car();
		User newUser = new User();
		newUser.setUsername(regData.getUsername());
		newUser.setPassword(regData.getPassword());
		newUser.setFirstname(regData.getFirstname());
		newUser.setLastname(regData.getLastname());
		newUser.setEmail(regData.getEmail());
		newUser.setRole(regData.getRoleselected().toUpperCase());
		
		if(regData.getRoleselected().equalsIgnoreCase("DRIVER")) {
			car.setMaxplaces(regData.getMaxplaces());
			car.setDeparturetime(regData.getDeparturetime());
			car.setDepartureplace(regData.getDepartureplace());
			regService.saveCar(car);
			newUser.setCar(car);
		}
		
		regService.saveUser(newUser);
		
		return new ResponseEntity<String>("registered", HttpStatus.OK);
	}
	
	
}
