package com.kwaku.demo.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double price;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;
    
    private LocalDateTime departureDateTime;
    private LocalDateTime returnDateTime;
    
    public Flight(long id, double price, Airport arrivalAirport, Airport departureAirport,
            LocalDateTime departureDateTime, LocalDateTime returnDateTime) {
        this.id = id;
        this.price = price;
        this.arrivalAirport = arrivalAirport;
        this.departureAirport = departureAirport;
        this.departureDateTime = departureDateTime;
        this.returnDateTime = returnDateTime;
    }

    public Flight() {
    }

    public long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public LocalDateTime getReturnDateTime() {
        return returnDateTime;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Flight [id=" + id + ", price=" + price + ", arrivalAirport=" + arrivalAirport + ", departureAirport="
                + departureAirport + ", departureDateTime=" + departureDateTime + ", returnDateTime=" + returnDateTime
                + "]";
    }

}
