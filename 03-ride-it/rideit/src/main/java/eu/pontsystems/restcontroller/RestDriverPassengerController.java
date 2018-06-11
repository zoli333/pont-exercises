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
import org.springframework.security.access.prepost.PreAuthorize;
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
	
	
	@GetMapping(path = {"/passengerPage/getallcar","driverPage/getallcar"},produces = "application/json")
	public ResponseEntity<?> getAllCars() {
		List<Car> cars = driverService.getCars();
		List<Car> outputCars = new ArrayList<>();
		for(Car c : cars) {
			int car_id = c.getCar_id();
			User driver = driverService.getDriverById(car_id);
			List<User> memberList = driverService.getMembers(car_id);
			
			if(driver == null && memberList.size()>0) {
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
				outputCars.add(c);
			}else if(driver != null && memberList.size() == 0) {
				String driverName = driver.getFirstname() + " " + driver.getLastname();
				c.setDriverEmailAddress(driver.getEmail());
				c.setDriverName(driverName);
				if(c.getDeparturetime()!=null && c.getDepartureplace() != null && new Integer(car_id)!=null) {
					String deptTimeStringFormat = c.getDeparturetime().toString();
					c.setDeparturetimestring(deptTimeStringFormat);
					c.setDepartureplacestring(driver.getDepartureplace());
				}
				outputCars.add(c);
			}else if(driver != null && memberList.size()>0) {
				String driverName = driver.getFirstname() + " " + driver.getLastname();
				c.setDriverEmailAddress(driver.getEmail());
				c.setDriverName(driverName);
				String deptTimeStringFormat = c.getDeparturetime().toString();
				c.setDeparturetimestring(deptTimeStringFormat);
				c.setDepartureplacestring(driver.getDepartureplace());
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
				outputCars.add(c);
			}
			
		}		
				
		return new ResponseEntity<>(outputCars, HttpStatus.OK);
		
	}
	
		
	@PostMapping(path = {"/driverPage/subscription","/passengerPage/subscription"}, consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> registerUser(@RequestBody SubscriptionData subScriptionData) throws ParseException {
		User userData = driverService.findByUsername(getLoggedInUserName());
		int carId = subScriptionData.getCar_id();
		if(userData.getRole().equalsIgnoreCase("PASSENGER")) {
			if(!(subScriptionData.getConnectiontime().equalsIgnoreCase("") || subScriptionData.getConnectionplace().equalsIgnoreCase(""))) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date parsedconTime=null;
				try {
					parsedconTime = formatter.parse(subScriptionData.getDeparturetime());	
				} catch (ParseException pe){
					return new ResponseEntity<String>("parseerror", HttpStatus.OK);
				} 
			    Timestamp conTime = new java.sql.Timestamp(parsedconTime.getTime());
			    userData.setConnectionTime(conTime);
				userData.setConnectionPlace(subScriptionData.getConnectionplace());
			} else {
				User driver = driverService.getDriverById(carId);
				userData.setConnectionTime(driver.getCar().getDeparturetime());
				userData.setConnectionPlace(driver.getCar().getDepartureplace());
			}
			driverService.subscribePassengerToCar(userData, carId);
		}else if(userData.getRole().equalsIgnoreCase("DRIVER")) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date parseddepTime=null;
			try {
				parseddepTime = formatter.parse(subScriptionData.getDeparturetime());	
			} catch (ParseException pe){
				return new ResponseEntity<String>("parseerror", HttpStatus.OK);
			} 
			Timestamp conTime = new java.sql.Timestamp(parseddepTime.getTime());
			userData.setDeparturetime(conTime);
			userData.setDepartureplace(subScriptionData.getDepartureplace());
			userData.setMaxplaces(subScriptionData.getMaxplaces());
			driverService.subscribeDriverToCar(userData);
		}
				
		
		
		return new ResponseEntity<String>("subscribed!!!!", HttpStatus.OK);
	}
	
	@PostMapping(path = {"/driverPage/removesubscription","passengerPage/removesubscription"}, consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> removeUser(@RequestBody SubscriptionData subScriptionData) throws ParseException {
		User userData = driverService.findByUsername(getLoggedInUserName());
		String userRole = userData.getRole();
		if(userRole.equalsIgnoreCase("DRIVER")) {
			driverService.RemoveDriverFromCar(userData);
		} else if(userRole.equalsIgnoreCase("PASSENGER")) {
			driverService.RemovePassengerFromCar(userData);	
		}
		return new ResponseEntity<String>("Successfully removed!!!!", HttpStatus.OK);
	}
	

	
	@GetMapping(path = {"/driverPage/numberofregisteredcars","/passengerPage/numberofregisteredcars"},produces = "application/json")
	public ResponseEntity<?> getNumberOfRegisteredCars() {
		int numberOfCars = driverService.getCars().size();
		return new ResponseEntity<>(numberOfCars, HttpStatus.OK);
		
	}
	
	@PostMapping(path = "/driverPage/registernewcar", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> registerNewCar(@RequestBody SubscriptionData subScriptionData) throws ParseException {
		User userData = driverService.findByUsername(getLoggedInUserName());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parseddepTime=null;
		try {
			parseddepTime = formatter.parse(subScriptionData.getDeparturetime());	
		} catch (ParseException pe){
			return new ResponseEntity<String>("parseerror", HttpStatus.OK);
		} 
		Timestamp depTime = new java.sql.Timestamp(parseddepTime.getTime());
		userData.setDeparturetime(depTime);
		userData.setDepartureplace(subScriptionData.getDepartureplace());
		userData.setMaxplaces(subScriptionData.getMaxplaces());
		driverService.subscribeDriverToCar(userData);
		return new ResponseEntity<String>("Successfully registered new car!!!!", HttpStatus.OK);
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

