package eu.pontsystems.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.pontsystems.entity.Car;
import eu.pontsystems.repository.CarRepository;

@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	public List<Car> getAllCars() {
		return carRepository.findAll(); 
	}
	
	public List<Car> getFreeCar() {
		return carRepository.getFreeCar();
	}
	
	public void saveCar(Car car) {
		carRepository.save(car);
	}
	
	public Car findCarById(Integer carId) {
		return carRepository.findByCarId(carId);
	}
}
