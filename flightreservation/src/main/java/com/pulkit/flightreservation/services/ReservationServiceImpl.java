package com.pulkit.flightreservation.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pulkit.flightreservation.dto.ReservationRequest;
import com.pulkit.flightreservation.entities.Flight;
import com.pulkit.flightreservation.entities.Passenger;
import com.pulkit.flightreservation.entities.Reservation;
import com.pulkit.flightreservation.repos.FlightRepository;
import com.pulkit.flightreservation.repos.PassengerRepository;
import com.pulkit.flightreservation.repos.ReservationRepository;
import com.pulkit.flightreservation.util.EmailUtil;
import com.pulkit.flightreservation.util.PdfGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Value("${com.pulkit.flightreservation.itinerary.dirpath}")
	private String ITINERARY_DIR;

	@Autowired
	FlightRepository flightRepos;
	
	@Autowired
	PassengerRepository passengerRepos;
	
	@Autowired
	ReservationRepository reservationRepos;
	
	@Autowired
	PdfGenerator pdfGenerator;
	
	@Autowired
	EmailUtil emailUtil;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest request) {
		
//		Make Payment
		LOGGER.info("Inside bookFlight()");
		long flightId = request.getFlightId();
		LOGGER.info("Fetching  flight for flight id: " + flightId);
		Flight flight = flightRepos.findById(flightId).get();
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setEmail(request.getPassengerEmail());
		passenger.setPhone(request.getPassengerPhone());
		LOGGER.info("Saving the passenger: " + passenger);
		Passenger savedPassenger = passengerRepos.save(passenger);
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		
		LOGGER.info("Saving the reservation: " + reservation);
		Reservation savedReservation = reservationRepos.save(reservation);
		
		String filePath = ITINERARY_DIR + savedReservation.getId() + ".pdf";
		LOGGER.info("Generating  the itinerary "+ filePath);
		pdfGenerator.generateItinerary(savedReservation, filePath);
		LOGGER.info("Emailing the Itinerary");
		emailUtil.sendItineary(passenger.getEmail() , filePath);
		return savedReservation;
	}

}
