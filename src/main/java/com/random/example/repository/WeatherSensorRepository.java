package com.random.example;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "WeatherSensors", path = "weatherSensors")
public interface WeatherSensorRepository extends MongoRepository<WeatherSensor, Long> {

    Optional<WeatherSensor> findById(Long id);

    List<WeatherSensor> findAll();


}


