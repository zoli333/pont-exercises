package eu.pontsystems.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class placeClass {
	
	public placeClass() {}
	
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private int id;
	
	private String connection_place;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConnection_place() {
		return connection_place;
	}

	public void setConnection_place(String connection_place) {
		this.connection_place = connection_place;
	}

		
	
	
}
