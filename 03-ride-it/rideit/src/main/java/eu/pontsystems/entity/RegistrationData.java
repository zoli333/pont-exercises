package eu.pontsystems.entity;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class RegistrationData {
	
	public RegistrationData() {}
	
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
	
	@Column(name="maxplaces")
	private int maxplaces;
	
	@Column(name="departuretime")
	private Timestamp departuretime;
	
	@Column(name="departureplace")
	private String departureplace;
	
	@Column(name="roleselected")
	private String roleselected;
	
	
	
	
	public String getRoleselected() {
		return roleselected;
	}



	public void setRoleselected(String roleselected) {
		this.roleselected = roleselected;
	}



	public int getMaxplaces() {
		return maxplaces;
	}

	public void setMaxplaces(int maxplaces) {
		this.maxplaces = maxplaces;
	}

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


	

	

	
	
	
}
