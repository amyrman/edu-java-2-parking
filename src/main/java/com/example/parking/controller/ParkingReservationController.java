package com.example.parking.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.parking.entity.Car;
import com.example.parking.entity.ParkingReservation;
import com.example.parking.entity.ParkingSpot;
import com.example.parking.repository.CarRepository;
import com.example.parking.repository.ParkingReservationRepository;
import com.example.parking.repository.ParkingSpotRepository;
import com.example.parking.service.validateStopTime;

@RestController
public class ParkingReservationController {

    ParkingReservationRepository pRepository;
    CarRepository carRepo;
    ParkingSpotRepository pSpotRepo;

    public ParkingReservationController(
        ParkingReservationRepository pRepository,
        CarRepository carRepo,
        ParkingSpotRepository pSpotRepo) {
        this.pRepository = pRepository;
        this.carRepo = carRepo;
        this.pSpotRepo = pSpotRepo;
    }

    @GetMapping("/parkingreservation")
    public Iterable<ParkingReservation> getAllReservations() {
        return pRepository.findAll();
    }

    @GetMapping("/parkingreservation/{id}")
    public Optional<ParkingReservation> getAllReservationsByID(@PathVariable("id") Long id) {
        var parkingReservationById = pRepository.findById(id);
        if (parkingReservationById.isPresent()) {
            return parkingReservationById;
        }
        return null;
    }

    @GetMapping("/parkingreservation/filter")
    public List<ParkingReservation> filterActiveParkings() {
        return pRepository.filterOnActive();
    }

    @PostMapping("/parkingreservation")
    public ParkingReservation addParkingReservation(@RequestBody ParkingReservation parkingReservation) {
        var stopTime = parkingReservation.getStopTime();

        if (validateStopTime.isInFuture(stopTime)) {
            Long carId = parkingReservation.getCar().getId();
            var carOptional = carRepo.findById(carId);
            if (carOptional.isPresent()) {
                Car car = carOptional.get();
                car.addParkingReservation(parkingReservation);
                carRepo.save(car);
            }
            Long parkingSpotId = parkingReservation.getParkingSpot().getId();
            var pSpotOptional = pSpotRepo.findById(parkingSpotId);
            if (pSpotOptional.isPresent()) {
                ParkingSpot parkingSpot = pSpotOptional.get();
                parkingSpot.addParkingReservation(parkingReservation);
                pSpotRepo.save(parkingSpot);
                return pRepository.save(parkingReservation);
            }
        }
        return null;
    }

    @PatchMapping("/parkingreservation/{id}")
    public ParkingReservation updateStopTime(@PathVariable("id") Long id,
            @RequestBody ParkingReservation parkingReservation) {

        var parkingReservationById = pRepository.findById(id);
        LocalDateTime updatedStopTime = parkingReservation.getStopTime();
        if (parkingReservationById.isPresent() && validateStopTime.isInFuture(updatedStopTime)) {
            ParkingReservation updatedParkingReservation = parkingReservationById.get();
            updatedParkingReservation.setStopTime(updatedStopTime);
            return pRepository.save(updatedParkingReservation);
        }
        return null;
    }
}
