package com.example.parking.repository;

import com.example.parking.entity.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
}
