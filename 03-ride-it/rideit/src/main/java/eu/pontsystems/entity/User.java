package eu.pontsystems.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.JoinColumn;
@Entity
@Table(name="user")
public class User{
	
	public User() {}

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int id;
	
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
	
	@Column(name="connection_time")
	private Timestamp connectionTime;
	
	@Column(name="connection_place")
	private String connectionPlace;
	
	@Column(name="role")
	private String role;
	
	@Transient
	private int maxplaces;
	
	@Transient
	private Timestamp departuretime;
	
	@Transient
	private String departureplace;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "car_id")
	@JsonIgnore
	private Car car;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public Timestamp getConnectionTime() {
		return connectionTime;
	}

	public void setConnectionTime(Timestamp connectionTime) {
		this.connectionTime = connectionTime;
	}

	public String getConnectionPlace() {
		return connectionPlace;
	}

	public void setConnectionPlace(String connectionPlace) {
		this.connectionPlace = connectionPlace;
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

	public int getMaxplaces() {
		return maxplaces;
	}

	public void setMaxplaces(int maxplaces) {
		this.maxplaces = maxplaces;
	}

	public Timestamp getDeparturetime() {
		return departuretime;
	}

	public void setDeparturetime(Timestamp departuretime) {
		this.departuretime = departuretime;
	}

	public String getDepartureplace() {
		return departureplace;
	}

	public void setDepartureplace(String departureplace) {
		this.departureplace = departureplace;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", email=" + email + ", connectionTime=" + connectionTime
				+ ", connectionPlace=" + connectionPlace + ", role=" + role + ", maxplaces=" + maxplaces
				+ ", departuretime=" + departuretime + ", departureplace=" + departureplace + ", car=" + car + "]";
	}

	
	
	
	

	
}
