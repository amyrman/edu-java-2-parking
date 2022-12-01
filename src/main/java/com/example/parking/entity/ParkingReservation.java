package com.example.parking.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ParkingReservation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private ParkingSpot parkingSpot;
   
    @ManyToOne
    private Car car;

    @CreationTimestamp
    private LocalDateTime startTime;

    private LocalDateTime stopTime;

    public boolean isActive = true;

    public boolean getActive() {
      return isActive;
    }

    public void setActive(boolean isActive) {
      this.isActive = isActive;
    }

    public LocalDateTime getStartTime() {
      return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
      this.startTime = startTime;
    }

    public LocalDateTime getStopTime() {
      return stopTime;
    }

    public void setStopTime(LocalDateTime stopTime) {
      this.stopTime = stopTime;
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public ParkingSpot getParkingSpot() {
      return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingspot) {
      this.parkingSpot = parkingspot;
    }

    public Car getCar() {
      return car;
    }

    public void setCar(Car car) {
      this.car = car;
    }
}
