package com.pulkit.flightreservation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulkit.flightreservation.dto.UpdateReservationRequest;
import com.pulkit.flightreservation.entities.Reservation;
import com.pulkit.flightreservation.repos.ReservationRepository;

@RestController
@CrossOrigin
public class ReservationRestController {

	@Autowired
	ReservationRepository reservationRepos;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);

	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable Long id) {
		LOGGER.info("Inside findReservation() with id: " + id);
		return reservationRepos.findById(id).get();
	}
	
	@RequestMapping("/reservations")
	public Reservation updateReservation(@RequestBody UpdateReservationRequest request) {
		LOGGER.info("Inside updateReservation() for " + request);
		Reservation reservation = reservationRepos.findById(request.getId()).get();
		reservation.setCheckedIn(request.getCheckedIn());
		reservation.setNumberOfBags(request.getNumberOfBags());
		LOGGER.info("Saving reservation");
		return reservationRepos.save(reservation);
	}

}
