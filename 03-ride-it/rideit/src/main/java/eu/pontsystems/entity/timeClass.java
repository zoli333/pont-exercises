package eu.pontsystems.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class timeClass {
	
	public timeClass() {}
	
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private int id;
	
	private Timestamp connection_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getConnection_time() {
		return connection_time;
	}

	public void setConnection_time(Timestamp connection_time) {
		this.connection_time = connection_time;
	}
	
	
}
