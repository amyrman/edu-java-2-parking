package com.example.parking.repository;

import com.example.parking.entity.ParkingSpot;

import org.springframework.data.repository.CrudRepository;

public interface ParkingSpotRepository extends CrudRepository<ParkingSpot, Long> {
}
