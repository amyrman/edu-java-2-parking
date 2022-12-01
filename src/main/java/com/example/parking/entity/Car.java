package com.example.parking.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reg;

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    @ManyToOne
    // TODO: Fix serialization (to not ignore)
    @JsonIgnore
    private Owner owner;

    @OneToMany(mappedBy = "car")
    private Set<ParkingReservation> parkingreservations = new HashSet<>();
    
    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addParkingReservation(ParkingReservation parkingreservation) {
        this.parkingreservations.add(parkingreservation);
        parkingreservation.setCar(this);
    }

    // Koppla en kolumn till owner;
}
