package com.pulkit.flightcheckin.integration;

import com.pulkit.flightcheckin.integration.dto.Reservation;
import com.pulkit.flightcheckin.integration.dto.UpdateReservationRequest;

public interface ReservationRestClient {

	public Reservation findReservation(Long id);
	
	public Reservation updateReservation(UpdateReservationRequest request);
	
}
