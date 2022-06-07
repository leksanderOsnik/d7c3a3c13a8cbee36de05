package com.random.example;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "WeatherSensorsMetrics", path = "weatherSensorsMetrics")
public interface WeatherSensorMetricsRepository extends MongoRepository<WeatherSensorMetrics, Long> {

    List<WeatherSensorMetrics> findAllBySensorId(Long sensorId);

    List<WeatherSensorMetrics> findAllBySensorIdAndTimestampGreaterThanOrderByTimestampDesc(Long sensorId,
                                                                                          long timestamp);
}

