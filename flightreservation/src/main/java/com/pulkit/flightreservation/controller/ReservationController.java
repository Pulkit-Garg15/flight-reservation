package com.pulkit.flightreservation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pulkit.flightreservation.dto.ReservationRequest;
import com.pulkit.flightreservation.entities.Flight;
import com.pulkit.flightreservation.entities.Reservation;
import com.pulkit.flightreservation.repos.FlightRepository;
import com.pulkit.flightreservation.services.ReservationService;

@Controller
public class ReservationController {
	
	@Autowired
	FlightRepository flightRepos;
	
	@Autowired
	ReservationService service;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

	@RequestMapping("/showCompleteReservation")
	public String showReservation(@RequestParam("flightId") Long flightId , ModelMap modelMap) {
		LOGGER.info("showReservation() invoked with Flight Id: " + flightId);
		Flight flight = flightRepos.findById(flightId).get();
		modelMap.addAttribute("flight" , flight);
		LOGGER.info("Flight is: " + flight);
		return "completeReservation";
		
	}
	
	@RequestMapping(value="/completeReservation" ,  method=RequestMethod.POST)
	public String completeReservation(ReservationRequest request , ModelMap modelMap) {
		LOGGER.info("completeReservation() " + request);
		Reservation reservation = service.bookFlight(request);
		modelMap.addAttribute("msg" , "Reservation completed with id: " + reservation.getId());
		return "reservationConfirmation";
	}
	
}
