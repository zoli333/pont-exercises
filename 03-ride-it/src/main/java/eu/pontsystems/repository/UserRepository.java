package eu.pontsystems.repository;

import java.util.List;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import eu.pontsystems.entity.Car;
import eu.pontsystems.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	
	public User findById(Integer id);
	
	public User findByUsername(String username);
	

	@Query("select u from User u where u.car.carId = :carId")
	List<User> getAllPassengers(@Param("carId") Integer carId);
	
	@Query("select u from User u where u.car.carId = :carId and u.role='DRIVER'")
	User getDriverById(@Param("carId") Integer carId);
	
	
	
	
}
