package eu.pontsystems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import eu.pontsystems.service.DriverPassengerLoginService;

@Controller
public class LoginController {
	
	/*
	@Autowired
	@Qualifier("driverPassengerDetailsService")
	DriverPassengerLoginService ps;
	*/
	@RequestMapping(value= {"/","/login","/home"},method=RequestMethod.GET)
	public ModelAndView welcome() {
	 	ModelAndView model = new ModelAndView();
	 	model.setViewName("login");
        return model;
	}	 
	
	@RequestMapping(value= "/login",method=RequestMethod.POST)
	public ModelAndView postLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
		System.out.println(username+","+password);
	 	ModelAndView model = new ModelAndView();
	 	model.setViewName("login");
        return model;
	}	
	
	@RequestMapping(value= "/register",method=RequestMethod.POST)
	public ModelAndView postRegister() {
		 	ModelAndView model = new ModelAndView();
		 	model.setViewName("registration");
	        return model;
	}
	
	@RequestMapping(value= "/register",method=RequestMethod.GET)
	public ModelAndView getRegister() {
		 	ModelAndView model = new ModelAndView();
		 	model.setViewName("registration");
	        return model;
	}	
	
	@RequestMapping(value= "/driverPage",method=RequestMethod.GET)
	public ModelAndView getDriverPage() {
		System.out.println("driverpage called!!!!!!");
		ModelAndView model = new ModelAndView();
	 	model.setViewName("driver");
        return model;
	}	
	
	@RequestMapping(value= "/passengerPage",method=RequestMethod.GET)
	public ModelAndView getPassengerPage() {
		ModelAndView model = new ModelAndView();
	 	model.setViewName("passenger");
        return model;
	}
	
}
