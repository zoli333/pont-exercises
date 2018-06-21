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
import eu.pontsystems.entity.User;
import eu.pontsystems.service.RegistrationService;

@RestController
public class RegistrationController {
	
	@Autowired
	RegistrationService registrationService;
	
	@PostMapping(path = "/register/newUser", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> registerUser(@RequestBody User newUser) {
		registrationService.saveUser(newUser);
		return new ResponseEntity<String>("registered", HttpStatus.OK);
	}
	
	
}
