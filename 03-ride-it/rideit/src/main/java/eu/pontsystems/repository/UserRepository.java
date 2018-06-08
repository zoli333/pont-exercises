package eu.pontsystems.repository;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import eu.pontsystems.entity.Car;
import eu.pontsystems.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	
	public User findByUsername(String username);
	
	
}
