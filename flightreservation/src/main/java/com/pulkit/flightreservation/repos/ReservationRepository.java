package com.pulkit.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulkit.flightreservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
