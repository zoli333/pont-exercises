package eu.pontsystems.repository;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import eu.pontsystems.entity.Car;
import eu.pontsystems.entity.User;
import eu.pontsystems.entity.placeClass;
import eu.pontsystems.entity.timeClass;


public interface DriverPassengerRepository {
	
	public User getDriverById(int carId);
	
	public List<User> getMembers(int carId);
	
	public void subscribePassengerToCar(User user, int carId);
	
	public void subscribeDriverToCar(User user);
	
	public List<timeClass> getMembersConnectionTime(int carId);
	
	public List<placeClass> getMembersConnectionPlace(int carId);
	
	public void RemovePassengerFromCar(User user);
	
	public void RemoveDriverFromCar(User user);
	
	public void RegisterNewCar(User user);
	
	
}
