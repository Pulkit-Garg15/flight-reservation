package com.pulkit.flightreservation.services;

import com.pulkit.flightreservation.dto.ReservationRequest;
import com.pulkit.flightreservation.entities.Reservation;

public interface ReservationService {

	public Reservation bookFlight(ReservationRequest request);
	
}
