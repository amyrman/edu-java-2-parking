package com.example.parking.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    @OneToMany(mappedBy = "owner")
    private Set<Car> cars = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public void addCar(Car car) {
        this.cars.add(car);
        car.setOwner(this);     
    }

}
