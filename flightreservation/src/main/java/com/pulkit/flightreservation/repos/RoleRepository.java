package com.pulkit.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulkit.flightreservation.entities.Role;

public interface RoleRepository extends JpaRepository<Role , Long> {

}
