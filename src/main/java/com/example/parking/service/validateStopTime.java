package com.example.parking.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class validateStopTime {
    public static boolean isInFuture(LocalDateTime stopTime){
        return stopTime.isAfter(LocalDateTime.now());   
    }
}
