package com.kwaku.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kwaku.demo.models.Airport;
import com.kwaku.demo.repositories.AirportRepository;
import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportController {

    @Autowired
    private AirportRepository airportRepository;


    @GetMapping
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    @GetMapping("/{id}")
    public Airport getAirportById(@PathVariable Long id) {
        return airportRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Airport createAirport(@RequestBody Airport airport) {
        return airportRepository.save(airport);
    }
    
    @PutMapping("/{id}")
    public Airport updateAirport(@PathVariable Long id, @RequestBody Airport updatedAirport) {
        if (airportRepository.existsById(id)) {
            updatedAirport.setId(id);
            return airportRepository.save(updatedAirport);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAirport(@PathVariable Long id) {
        airportRepository.deleteById(id);
    }
}
