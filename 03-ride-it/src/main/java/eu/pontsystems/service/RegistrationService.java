package eu.pontsystems.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.pontsystems.entity.Car;
import eu.pontsystems.entity.User;
import eu.pontsystems.repository.CarRepository;
import eu.pontsystems.repository.UserRepository;

@Service
public class RegistrationService {
		
	@Autowired
	private UserRepository userRepository;
	
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	
}
