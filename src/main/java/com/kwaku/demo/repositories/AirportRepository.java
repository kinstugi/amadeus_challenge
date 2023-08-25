package com.kwaku.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kwaku.demo.models.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    Airport findByCity(String city);
}
