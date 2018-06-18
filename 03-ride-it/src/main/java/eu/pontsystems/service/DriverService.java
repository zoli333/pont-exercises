package eu.pontsystems.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.mysql.fabric.xmlrpc.base.Array;

import eu.pontsystems.controller.LoginController;
import eu.pontsystems.entity.Car;
import eu.pontsystems.entity.User;
import eu.pontsystems.repository.CarRepository;

@Service
public class DriverService {
	
	@Autowired
	private CarService carService;
	
	@Autowired
	private UserService userService;
	
	public Map<Integer, Object> getAllCars() {
		List<Car> allCarList =  carService.getAllCars();
		for(Car car : allCarList) {
			Integer carId = car.getCarId();
			List<User> passengerList = userService.getAllPassengers(carId);
			if(passengerList.size()>0) {
				car.setPassengerList(passengerList);	
			}else {
				car.setPassengerList(new ArrayList<>());
			}
		}
		Map<Integer,Object> allCars = new HashMap<>();
		allCars.put(LoginController.userId,allCarList);
		return allCars;
	}
	
	public void registerNewCar(User newCar) {
		User newDriver = userService.findById(LoginController.userId);
		Car freeCar = carService.getFreeCar();
		newDriver.setDepartureTime(newCar.getDepartureTime());
		newDriver.setDepartureCity(newCar.getDepartureCity());
		newDriver.setDepartureAddress(newCar.getDepartureAddress());
		
		newDriver.setDestinationTime(newCar.getDestinationTime());
		newDriver.setDestinationCity(newCar.getDestinationCity());
		newDriver.setDestinationAddress(newCar.getDestinationAddress());
		if(freeCar!=null) {
			//found free car
			freeCar.setMaxplaces(newCar.getMaxplaces());
			newDriver.setCar(freeCar);
			userService.saveUser(newDriver);
		}else {
			//not found free Car (maxplaces=0), so create a new one, with maxplaces defined
			Car emptyCar = new Car();
			emptyCar.setMaxplaces(newCar.getMaxplaces());
			newDriver.setCar(emptyCar);
			userService.saveUser(newDriver);
		}
		
	}
	
	public void removeFromCar(Integer carId, Integer userId) {
		User user = findUserById(userId);
		Car car = findCarById(carId);
		car.setMaxplaces(0);
		carService.saveCar(car);
		user.setCar(null);
		userService.saveUser(user);
		
	}
	

	public boolean applyDriver(Integer carId, Integer userId, User existingCar) {
		User user = findUserById(userId);
		if(user.getCar()!=null) {
			//if the user has been already applied to a car as driver			
			return false;
		}else {
			//the user is free to apply for a car (becuase he is not has been assigned to an already existing car)
			user.setDepartureTime(existingCar.getDepartureTime());
			user.setDepartureCity(existingCar.getDepartureCity());
			user.setDepartureAddress(existingCar.getDepartureAddress());
			
			user.setDestinationTime(existingCar.getDestinationTime());
			user.setDestinationCity(existingCar.getDestinationCity());
			user.setDestinationAddress(existingCar.getDestinationAddress());
			Car car = carService.findCarById(carId);
			car.setMaxplaces(existingCar.getMaxplaces());
			user.setCar(car);
			userService.saveUser(user);
			return true;
		}
	}

	
	/*
	public boolean applyDriver(User existingCar, Integer userId) {
		User user = findUserById(userId);
		if(user.getCar().getCarId()!=null) {
			//if the user has been already applied to a car as driver			
			return false;
		}else {
			//the user is free to apply for a car (becuase he is not has been assigned to an already existing car)
			user.setDepartureTime(existingCar.getDepartureTime());
			user.setDepartureCity(existingCar.getDepartureCity());
			user.setDepartureAddress(existingCar.getDepartureAddress());
			
			user.setDestinationTime(existingCar.getDestinationTime());
			user.setDestinationCity(existingCar.getDestinationCity());
			user.setDestinationAddress(existingCar.getDestinationAddress());
			
			userService.saveUser(user);
		}
	}
	*/
	public User findUserById(Integer id) {
		return userService.findById(id);
	}
	
	public Car findCarById(Integer carId) {
		return carService.findCarById(carId);
	}
	
	public Car getFreeCar() {
		return carService.getFreeCar();
	}
	
	public void saveUser(User user) {
		userService.saveUser(user);
	}
	
	public void saveCar(Car car) {
		carService.saveCar(car);
	}

	
}
