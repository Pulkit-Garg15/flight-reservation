package com.pulkit.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulkit.flightreservation.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
