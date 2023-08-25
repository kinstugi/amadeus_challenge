package com.kwaku.demo.scheduled;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kwaku.demo.models.Airport;
import com.kwaku.demo.models.Flight;
import com.kwaku.demo.repositories.AirportRepository;
import com.kwaku.demo.repositories.FlightRepository;

@Service
public class FetchFlightsTask {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    public FetchFlightsTask(FlightRepository flightRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }

    //@Scheduled(cron = "0 0/5 * * * *") // Run every 5 minutes
    @Scheduled(cron = "0 0 0 * * *") 
    public void runScheduledTask() {
        // some api call is made here
        Airport airport1 = new Airport("Istanbul"); // Set the appropriate ID
        airportRepository.save(airport1);

        Airport airport2 = new Airport("Accra"); // Set the appropriate ID
        airportRepository.save(airport2);
        
        List<Flight> flights = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            LocalDateTime departureDateTime = LocalDateTime.now().plusDays(i);
            LocalDateTime returnDateTime = LocalDateTime.now().plusDays(i + 1);
            double price = 100 * i;
            Flight flight = new Flight(price, airport2, airport1, departureDateTime, returnDateTime);
            flights.add(flight);
        }
        flightRepository.saveAll(flights);
    }
}
