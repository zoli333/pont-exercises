package eu.pontsystems.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SubscriptionData {
		
		public SubscriptionData() {}
		
		

		public SubscriptionData(int id, int car_id, String connectiontime, String connectionplace, String departuretime,
				String departureplace, int maxplaces) {
			this.id = id;
			this.car_id = car_id;
			this.connectiontime = connectiontime;
			this.connectionplace = connectionplace;
			this.departuretime = departuretime;
			this.departureplace = departureplace;
			this.maxplaces = maxplaces;
		}



		@Id
		@GeneratedValue
		private int id;
		
		private int car_id;
		private String connectiontime;
		private String connectionplace;
		private String departuretime;
		private String departureplace;
		private int maxplaces;
		
		
		
		public int getMaxplaces() {
			return maxplaces;
		}



		public void setMaxplaces(int maxplaces) {
			this.maxplaces = maxplaces;
		}



		public String getDeparturetime() {
			return departuretime;
		}




		public void setDeparturetime(String departuretime) {
			this.departuretime = departuretime;
		}




		public String getDepartureplace() {
			return departureplace;
		}




		public void setDepartureplace(String departureplace) {
			this.departureplace = departureplace;
		}




		public String getConnectiontime() {
			return connectiontime;
		}

		public void setConnectiontime(String connectiontime) {
			this.connectiontime = connectiontime;
		}

		public String getConnectionplace() {
			return connectionplace;
		}

		public void setConnectionplace(String connectionplace) {
			this.connectionplace = connectionplace;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getCar_id() {
			return car_id;
		}

		public void setCar_id(int car_id) {
			this.car_id = car_id;
		}
		
		
		

}
