package com.random.example;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface WeatherSensorRepository extends MongoRepository<WeatherSensor, String> {

    Optional<WeatherSensor> findById(String id);

    List<WeatherSensor> findAll();


}


