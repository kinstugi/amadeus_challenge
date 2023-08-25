package com.kwaku.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kwaku.demo.models.Airport;
import com.kwaku.demo.models.Flight;
import com.kwaku.demo.repositories.AirportRepository;
import com.kwaku.demo.repositories.FlightRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private AirportRepository airportRepository;

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @GetMapping("/{id}")
    public Flight getFlightById(@PathVariable Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Flight createFlight(@RequestBody Flight flight) {
        return flightRepository.save(flight);
    }

    @PutMapping("/{id}")
    public Flight updateFlight(@PathVariable Long id, @RequestBody Flight updatedFlight) {
        if (flightRepository.existsById(id)) {
            updatedFlight.setId(id);
            return flightRepository.save(updatedFlight);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightRepository.deleteById(id);
    }

    
    @GetMapping("/search")
    public List<List<Flight>> searchFlights(
            @RequestParam String departureAirportCode,
            @RequestParam String arrivalAirportCode,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDateTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime returnDateTime) {

        Airport departureAirport = airportRepository.findByCity(departureAirportCode);
        Airport arrivalAirport = airportRepository.findByCity(arrivalAirportCode);

        if (departureAirport == null || arrivalAirport == null) {
            // Handle case where airports are not found
            return Collections.emptyList();
        }

        if (returnDateTime == null) {
            // One-way flight
            List<Flight> oneWayFlights = flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTime(
                    departureAirport, arrivalAirport, departureDateTime);
            return Collections.singletonList(oneWayFlights);
        } else {
            // Two-way flight
            List<Flight> oneWayFlights = flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTime(
                    departureAirport, arrivalAirport, departureDateTime);

            List<Flight> returnFlights = flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTime(
                    arrivalAirport, departureAirport, returnDateTime);

            return Arrays.asList(oneWayFlights, returnFlights);
        }
    }

}   
