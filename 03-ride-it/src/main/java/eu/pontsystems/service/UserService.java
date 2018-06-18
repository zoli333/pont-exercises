package eu.pontsystems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.pontsystems.entity.User;
import eu.pontsystems.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public Integer getUserId(String loggedInUserName) {
		User user = userRepository.findByUsername(loggedInUserName);
		return user.getId();
	}
	
	public List<User> getAllPassengers(Integer carId) {
		return userRepository.getAllPassengers(carId);
	}
	
	public User findById(Integer id) {
		return userRepository.findById(id);
	}
	
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	
}
