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
	private int car_id;
	
	@Column(name="maxplaces")
	private int maxplaces;
	
	@Column(name="departuretime")
	private Timestamp departuretime;
	
	@Column(name="departureplace")
	private String departureplace;
	
	@OneToMany(mappedBy = "car",
		        cascade = CascadeType.ALL
	)
	@JsonIgnore
	private List<User> users;
	
	@Transient
	private String departuretimestring;
	
	@Transient
	private String departureplacestring;
	
	@Transient
	private String connectiontimestring;
	
	@Transient
	private String connectionplacestring;
	
	@Transient
	private String members;
	
	@Transient
	private String driverName;
	
	@Transient
	private String driverEmailAddress;
	
	
	
	
	public String getDepartureplacestring() {
		return departureplacestring;
	}

	public void setDepartureplacestring(String departureplacestring) {
		this.departureplacestring = departureplacestring;
	}

	public String getConnectiontimestring() {
		return connectiontimestring;
	}

	public void setConnectiontimestring(String connectiontimestring) {
		this.connectiontimestring = connectiontimestring;
	}

	public String getConnectionplacestring() {
		return connectionplacestring;
	}

	public void setConnectionplacestring(String connectionplacestring) {
		this.connectionplacestring = connectionplacestring;
	}

	public String getDriverEmailAddress() {
		return driverEmailAddress;
	}

	public void setDriverEmailAddress(String driverEmailAddress) {
		this.driverEmailAddress = driverEmailAddress;
	}

	public String getMembers() {
		return members;
	}

	public void setMembers(String members) {
		this.members = members;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDeparturetimestring() {
		return departuretimestring;
	}

	public void setDeparturetimestring(String departuretimestring) {
		this.departuretimestring = departuretimestring;
	}

	public int getCar_id() {
		return car_id;
	}

	public void setCar_id(int car_id) {
		this.car_id = car_id;
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
