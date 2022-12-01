package com.example.parking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.parking.entity.ParkingReservation;

public interface ParkingReservationRepository extends CrudRepository<ParkingReservation, Long>{

  @Query("""
            SELECT p FROM ParkingReservation p WHERE (p.isActive) = true
            """)
    List<ParkingReservation> filterOnActive();
  
}
