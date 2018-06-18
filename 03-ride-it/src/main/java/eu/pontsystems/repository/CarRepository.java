package eu.pontsystems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import eu.pontsystems.entity.Car;
import eu.pontsystems.entity.User;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>{
	
	//@Query("select c.users from car c where c.car_id = :carId")
	
	public Car findByCarId(Integer carId);
	
	@Query("select c from Car c where c.maxplaces=0")
	public Car getFreeCar();
	
}
