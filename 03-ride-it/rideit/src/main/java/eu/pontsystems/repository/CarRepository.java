package eu.pontsystems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import eu.pontsystems.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>{
	
	//@Query("select c.users from car c where c.car_id = :carId")
	
	
}
