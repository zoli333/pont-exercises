package eu.pontsystems.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="user")
public class User{
	
	public User() {}

	
	public User(Integer id, String username, String password, String firstname, String lastname, String email,
			Date departureDate, Time departureTime, String departureCity, String departureAddress, Date destinationDate,
			Time destinationTime, String destinationCity, String destinationAddress, String role, Car car,
			int maxplaces) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.departureCity = departureCity;
		this.departureAddress = departureAddress;
		this.destinationDate = destinationDate;
		this.destinationTime = destinationTime;
		this.destinationCity = destinationCity;
		this.destinationAddress = destinationAddress;
		this.role = role;
		this.car = car;
		this.maxplaces = maxplaces;
	}




	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="email")
	private String email;
	
	@Column(name="departure_date")
	private Date departureDate;
	
	@Column(name="departure_time")
	private Time departureTime;
	
	@Column(name="departure_city")
	private String departureCity;
	
	@Column(name="departure_address")
	private String departureAddress;
	
	@Column(name="destination_date")
	private Date destinationDate;
	
	@Column(name="destination_time")
	private Time destinationTime;
	
	@Column(name="destination_city")
	private String destinationCity;
	
	@Column(name="destination_address")
	private String destinationAddress;
		
	@Column(name="role")
	private String role;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "car_id")
	private Car car;
	
	@Transient
	private int maxplaces;
	
	
	
	public int getMaxplaces() {
		return maxplaces;
	}




	public void setMaxplaces(int maxplaces) {
		this.maxplaces = maxplaces;
	}




	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}

	public String getDepartureAddress() {
		return departureAddress;
	}

	public void setDepartureAddress(String departureAddress) {
		this.departureAddress = departureAddress;
	}


	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}


	public Date getDepartureDate() {
		return departureDate;
	}


	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}


	public Time getDepartureTime() {
		return departureTime;
	}


	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}


	public Date getDestinationDate() {
		return destinationDate;
	}


	public void setDestinationDate(Date destinationDate) {
		this.destinationDate = destinationDate;
	}


	public Time getDestinationTime() {
		return destinationTime;
	}


	public void setDestinationTime(Time destinationTime) {
		this.destinationTime = destinationTime;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", email=" + email + ", departureDate=" + departureDate
				+ ", departureTime=" + departureTime + ", departureCity=" + departureCity + ", departureAddress="
				+ departureAddress + ", destinationDate=" + destinationDate + ", destinationTime=" + destinationTime
				+ ", destinationCity=" + destinationCity + ", destinationAddress=" + destinationAddress + ", role="
				+ role + ", car=" + car + ", maxplaces=" + maxplaces + "]";
	}
	
	
	
}
