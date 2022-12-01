package com.example.parking.controller;

import org.hibernate.Hibernate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.parking.entity.ParkingSpot;
import com.example.parking.repository.ParkingSpotRepository;

@RestController
public class ParkingSpotController {
  
  ParkingSpotRepository parkingspotrepo;

  public ParkingSpotController(ParkingSpotRepository spotrepo) {
    this.parkingspotrepo = spotrepo;
  }

  @PostMapping("/parkingspots")
    public ParkingSpot addParkingSpot(@RequestBody ParkingSpot spot) {
        return parkingspotrepo.save(spot);
    }

    @GetMapping("/parkingspots")
    public Iterable<ParkingSpot> getParkingSpots() {
        return parkingspotrepo.findAll();
    }

    @GetMapping("/parkingspots/{id}")
    public ResponseEntity<ParkingSpot> getParkingSpot(@PathVariable("id") Long id) {
        var parkingById = parkingspotrepo.findById(id);
        if(parkingById.isPresent()) {
            Hibernate.initialize(parkingById.get());
            return ResponseEntity.ok().body(parkingById.get());
        }
        return ResponseEntity.notFound().build();
    }
}
