package com.pulkit.flightcheckin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pulkit.flightcheckin.integration.ReservationRestClient;
import com.pulkit.flightcheckin.integration.dto.Reservation;
import com.pulkit.flightcheckin.integration.dto.UpdateReservationRequest;

@Controller
public class CheckInController {

	@Autowired
	ReservationRestClient restClient;
	
	@RequestMapping("/showStartCheckin")
	public String showStartCheckin() {
		return "startCheckIn";
	}
	
	@RequestMapping("/startCheckIn")
	public String startCheckIn(@RequestParam("reservationId") Long reservationId , ModelMap modelMap) {
		Reservation reservation = restClient.findReservation(reservationId);
		modelMap.addAttribute("reservation", reservation);
		return "displayReservationDetails";
	}
	
	@Transactional
	@RequestMapping("/completeCheckIn")
	public String completeCheckIn(@RequestParam("reservationId") Long reservationId ,@RequestParam("numberOfBags") int numberOfBags) {
		UpdateReservationRequest updateReservationRequest = new UpdateReservationRequest();
		updateReservationRequest.setId(reservationId);
		updateReservationRequest.setCheckedIn(true);
		updateReservationRequest.setNumberOfBags(numberOfBags);
		restClient.updateReservation(updateReservationRequest);
		return "checkInConfirmation";
	}
	
}
