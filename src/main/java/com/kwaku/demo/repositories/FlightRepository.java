package com.kwaku.demo.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kwaku.demo.models.Airport;
import com.kwaku.demo.models.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureDateTime(
            Airport departureAirport, Airport arrivalAirport, LocalDateTime departureDateTime);
}
