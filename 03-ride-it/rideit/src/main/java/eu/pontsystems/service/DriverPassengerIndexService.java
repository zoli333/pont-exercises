package eu.pontsystems.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.pontsystems.entity.Car;
import eu.pontsystems.entity.User;
import eu.pontsystems.entity.placeClass;
import eu.pontsystems.entity.timeClass;
import eu.pontsystems.repository.CarRepository;
import eu.pontsystems.repository.DriverPassengerRepository;
import eu.pontsystems.repository.UserRepository;

@Service
@Transactional
public class DriverPassengerIndexService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private DriverPassengerRepository dirverPassengerRepository;
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	public List<Car> getCars() {
		return carRepository.findAll();
	}
	
	public User getDriverById(int carId) {
		return dirverPassengerRepository.getDriverById(carId);
	}
	
	public List<User> getMembers(int carId) {
		return dirverPassengerRepository.getMembers(carId);
	}
	
	public List<timeClass> getMembersConnectionTime(int carId){
		return dirverPassengerRepository.getMembersConnectionTime(carId);
	}
	
	public List<placeClass> getMembersConnectionPlace(int carId){
		return dirverPassengerRepository.getMembersConnectionPlace(carId);
	}
	
	public void subscribePassengerToCar(User user, int carId) {
		dirverPassengerRepository.subscribePassengerToCar(user, carId);
	}
	

	
	public void RemovePassengerFromCar(User user) {
		dirverPassengerRepository.RemovePassengerFromCar(user);
	}

		
}
