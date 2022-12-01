package com.example.parking.controller;

import com.example.parking.entity.Car;
import com.example.parking.entity.Owner;
import com.example.parking.repository.CarRepository;
import com.example.parking.repository.OwnerRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
public class OwnerController {

    Logger logger = LogManager.getLogger(OwnerController.class);
    OwnerRepository ownerrepo;
    CarRepository carrepo;

    public OwnerController(OwnerRepository ownerrepo, CarRepository carrepo) {
        this.ownerrepo = ownerrepo;
        this.carrepo = carrepo;
    }

    @GetMapping("/owners/{id}")
    public ResponseEntity<Owner> getOwner(@PathVariable("id") Long id) {
        var ownerById = ownerrepo.findById(id);
        logger.info("After loading owner data, do we have everything?");
        if (ownerById.isPresent()) {
            Hibernate.initialize(ownerById.get().getCars());
            return ResponseEntity.ok().body(ownerById.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/owners")
    public Owner addOwner(@RequestBody Owner owner) {
        return ownerrepo.save(owner);
    }

    @GetMapping("/owners")
    public Iterable<Owner> getOwners() {
        return ownerrepo.findAll();
    }

    @PostMapping("/owners/{Id}/cars")
    public Owner createCar(@PathVariable("Id") Long id, @RequestBody Car car) {
        Optional<Owner> ownerById = ownerrepo.findById(id);
        if (ownerById.isPresent()) {
            Owner owner = ownerById.get();
            owner.addCar(car);
            carrepo.save(car);
            return ownerrepo.save(owner);
        }
        return null;
    }

    @GetMapping("/cars")
    public Iterable<Car> getCars() {
        return carrepo.findAll();
    }

    @GetMapping("/cars/{Id}")
    public Car getCar(@PathVariable("Id") Long id) {
        Optional<Car> carById = carrepo.findById(id);
        if (carById.isPresent()) {
            return carById.get();
        }
        return null;
    }
}
