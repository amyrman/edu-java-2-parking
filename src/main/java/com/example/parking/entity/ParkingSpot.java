package com.example.parking.entity;

import java.util.HashSet;
import java.util.Set;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class ParkingSpot {
  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private Long Id;

  @OneToMany(mappedBy = "parkingSpot")
    private Set<ParkingReservation> parkingreservations = new HashSet<>();

  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  private Point<G2D> coordinate;

  public Point<G2D> getCoordinate() {
    return coordinate;
  }

  public void setCoordinate(Point<G2D> coordinate) {
    this.coordinate = coordinate;
  }

  public void addParkingReservation(ParkingReservation parkingreservation) {
    this.parkingreservations.add(parkingreservation);
    parkingreservation.setParkingSpot(this);
}

}
