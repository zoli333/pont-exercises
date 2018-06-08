package eu.pontsystems.repository;

import java.sql.Timestamp;
import java.util.List;



import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.type.TimestampType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import eu.pontsystems.entity.Car;
import eu.pontsystems.entity.User;
import eu.pontsystems.entity.placeClass;
import eu.pontsystems.entity.timeClass;

@Repository
public class DriverPassengerRepositoryImpl implements DriverPassengerRepository {
	
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	@Transactional
	public User getDriverById(int carId) {
		System.out.println("check happened!!!!!!");
		Query q = entityManager.createNativeQuery("select u.* from user u where u.car_id = :carId and u.role='DRIVER'", User.class);
		q.setParameter("carId", carId);
		User user = (User)q.getSingleResult();
		return user;
	}


	@Override
	@Transactional
	public List<User> getMembers(int carId) {
		System.out.println("GETTING MEMBERS !!!!!");
		Query q = entityManager.createNativeQuery("select u.* from user u where u.car_id = :carId and u.role = 'PASSENGER'", User.class);
		q.setParameter("carId", carId);
		List<User> results = ((List<User>)q.getResultList());
		return results;
	}


	@Override
	public void subscribePassengerToCar(User user, int carId) {
		User updateUser = entityManager.find(User.class, user.getId());
		Query q = entityManager.createNativeQuery("select c.* from car c where c.car_id = :carId", Car.class);
		q.setParameter("carId", carId);
		Car newCar = (Car)q.getSingleResult();
		updateUser.setConnectionTime(user.getConnectionTime());
		updateUser.setConnectionPlace(user.getConnectionPlace());
		updateUser.setCar(newCar);
		entityManager.merge(updateUser);
		
	}


	
	@Override
	public List<timeClass> getMembersConnectionTime(int carId) {
		Query q = entityManager.createNativeQuery("select u.user_id, u.connection_time from user u where u.car_id = :carId and u.role = 'PASSENGER'", timeClass.class);
		q.setParameter("carId", carId);
		List<timeClass> results = ((List<timeClass>)q.getResultList());
		return results;
	}


	@Override
	public List<placeClass> getMembersConnectionPlace(int carId) {
		Query q = entityManager.createNativeQuery("select u.user_id, u.connection_place from user u where u.car_id = :carId and u.role = 'PASSENGER'", placeClass.class);
		q.setParameter("carId", carId);
		List<placeClass> results = ((List<placeClass>)q.getResultList());
		return results;
	}


	@Override
	public void RemovePassengerFromCar(User user) {
		User updateUser = entityManager.find(User.class, user.getId());
		updateUser.setCar(null);
		updateUser.setConnectionTime(null);
		updateUser.setConnectionPlace(null);
		entityManager.merge(updateUser);
	}


	
	
}
