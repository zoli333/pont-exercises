package eu.pontsystems.restcontroller;

import java.sql.Timestamp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;

import eu.pontsystems.entity.Car;
import eu.pontsystems.entity.SubscriptionData;
import eu.pontsystems.entity.User;
import eu.pontsystems.entity.placeClass;
import eu.pontsystems.entity.timeClass;
import eu.pontsystems.service.DriverPassengerIndexService;

@RestController
public class RestDriverPassengerController {
	
	@Autowired
	DriverPassengerIndexService driverService;
	
	
	
	
	@GetMapping(path = "/index/getallcar",produces = "application/json")
	public ResponseEntity<?> getAllCars() {
		
		//System.out.println(getLoggedInUserName());
		List<Car> cars = driverService.getCars();
		
		for(Car c : cars) {
			String deptTimeStringFormat = c.getDeparturetime().toString();
			c.setDeparturetimestring(deptTimeStringFormat);
			int car_id = c.getCar_id();
			User driver = driverService.getDriverById(car_id);
			String driverName = driver.getFirstname() + " " + driver.getLastname();
			c.setDriverEmailAddress(driver.getEmail());
			c.setDriverName(driverName);
			List<User> memberList = driverService.getMembers(car_id);
			
			StringBuilder members = new StringBuilder();
			for(User u : memberList) {
				members.append(u.getFirstname() + " " + u.getLastname() + " - " + u.getEmail().toString() + ",");
			}
			
			StringBuilder membersConnectionTimeString = new StringBuilder();
			List<timeClass> membersConnectionTime = driverService.getMembersConnectionTime(car_id);
			for(timeClass t : membersConnectionTime) {
				membersConnectionTimeString.append(t.getConnection_time().toString() + ",");
			}
			
			StringBuilder membersConnectionPlaceString = new StringBuilder();
			List<placeClass> membersConnectionPlace = driverService.getMembersConnectionPlace(car_id);
			for(placeClass p : membersConnectionPlace) {
				membersConnectionPlaceString.append(p.getConnection_place() + ",");
			}
			
			c.setMembers(members.toString());
			c.setConnectiontimestring(membersConnectionTimeString.toString());
			c.setConnectionplacestring(membersConnectionPlaceString.toString());
			
		}
		return new ResponseEntity<>(cars, HttpStatus.OK);
		
	}
	
		
	@PostMapping(path = "/index/subscription", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> registerUser(@RequestBody SubscriptionData subScriptionData) throws ParseException {
		User userData = driverService.findByUsername(getLoggedInUserName());
		int carId = subScriptionData.getCar_id();
		if(!(subScriptionData.getConnectiontime().equalsIgnoreCase("") || subScriptionData.getConnectionplace().equalsIgnoreCase(""))) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date parsedconTime = formatter.parse(subScriptionData.getConnectiontime());
		    Timestamp conTime = new java.sql.Timestamp(parsedconTime.getTime());
		    userData.setConnectionTime(conTime);
			userData.setConnectionPlace(subScriptionData.getConnectionplace());
		} else {
			User driver = driverService.getDriverById(carId);
			userData.setConnectionTime(driver.getCar().getDeparturetime());
			userData.setConnectionPlace(driver.getCar().getDepartureplace());
		}
		
		driverService.subscribePassengerToCar(userData, carId);
		return new ResponseEntity<String>("subscribed!!!!", HttpStatus.OK);
	}
	
	@PostMapping(path = "/index/removesubscription", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> removeUser(@RequestBody SubscriptionData subScriptionData) throws ParseException {
		User userData = driverService.findByUsername(getLoggedInUserName());
		
		driverService.RemovePassengerFromCar(userData);
		return new ResponseEntity<String>("Successfully removed!!!!", HttpStatus.OK);
	}
	
	private String getLoggedInUserName() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		return principal.toString();
	}
	
}

