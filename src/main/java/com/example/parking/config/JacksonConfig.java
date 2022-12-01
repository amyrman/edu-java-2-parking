package com.example.parking.config;

import org.geolatte.geom.json.GeolatteGeomModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

  @Bean
	GeolatteGeomModule geolatteModule(){
	return new GeolatteGeomModule();
	}

}
