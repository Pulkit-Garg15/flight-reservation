package com.pulkit.flightreservation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pulkit.flightreservation.entities.User;
import com.pulkit.flightreservation.repos.UserRepository;
import com.pulkit.flightreservation.services.SecurityService;

@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepos;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	SecurityService securityService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@RequestMapping("/showReg")
	public String showRegistrationPage() {
		LOGGER.info("Inside showRegistrationPage()");
		return "login/registerUser";
	}
	
	@RequestMapping(value="/registerUser" , method=RequestMethod.POST)
	public String register(@ModelAttribute("user") User user) {
		LOGGER.info("Inside register()" + user);
		user.setPassword(encoder.encode(user.getPassword()));
		userRepos.save(user);
		return "login/login";
	}
	
	@RequestMapping("/showLogin")
	public String showLoginPage() {
		LOGGER.info("Inside showLoginPage()");
		return "login/login";
	}
	
	@RequestMapping(value="/login" , method = RequestMethod.POST)
	public String login(@RequestParam("email") String email , @RequestParam("password") String password , ModelMap map) {
		boolean loginRequest = securityService.login(email, password);
		LOGGER.info("Inside login() and the email is: " + email);
		if(loginRequest) {
			return "findFlights";
		} else {
			map.addAttribute("msg" , "Invalid User Name or Password. Please try again.");
		}
		return "login/login";
	}
}
