package eu.pontsystems.entity;

import java.sql.Timestamp;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.JoinColumn;


@Entity
@Table(name="car")
public class Car  {
	
	public Car () {}
	


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="car_id")
	private Integer carId;
	
	@Column(name="maxplaces")
	private int maxplaces;
	
	@OneToMany(mappedBy = "car",
		        cascade = CascadeType.ALL,
		        fetch=FetchType.EAGER
		        
	)
	@JsonIgnore
	private List<User> users;

	@Transient
	private List<User> passengerList;
	
	
	
	public List<User> getPassengerList() {
		return passengerList;
	}

	public void setPassengerList(List<User> passengerList) {
		this.passengerList = passengerList;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public int getMaxplaces() {
		return maxplaces;
	}

	public void setMaxplaces(int maxplaces) {
		this.maxplaces = maxplaces;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Car [carId=" + carId + ", maxplaces=" + maxplaces + ", users=" + users + ", passengerList="
				+ passengerList + "]";
	}
	
	
	
	
		
	
	
}
