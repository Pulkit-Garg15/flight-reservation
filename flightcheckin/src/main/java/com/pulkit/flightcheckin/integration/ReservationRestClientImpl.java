package com.pulkit.flightcheckin.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.pulkit.flightcheckin.integration.dto.Reservation;
import com.pulkit.flightcheckin.integration.dto.UpdateReservationRequest;

@Component
public class ReservationRestClientImpl implements ReservationRestClient {

	@Value("${com.pulkit.flightcheckin.rest.url}")
	private String RESERVATION_REST_URL;

	@Override
	public Reservation findReservation(Long id) {
		RestTemplate restTemplate = new RestTemplate();
		Reservation reservation = restTemplate
				.getForObject(RESERVATION_REST_URL + id, Reservation.class);
		return reservation;
	}

	@Override
	public Reservation updateReservation(UpdateReservationRequest request) {
		RestTemplate restTemplate = new RestTemplate();
		Reservation reservation = restTemplate.postForObject(RESERVATION_REST_URL,
				request, Reservation.class);
		return reservation;
	}

}
