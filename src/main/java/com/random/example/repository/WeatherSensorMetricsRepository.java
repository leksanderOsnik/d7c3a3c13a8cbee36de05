package com.random.example.repository;

import com.random.example.entity.WeatherSensorMetrics;
import net.minidev.json.JSONObject;
import org.springframework.data.mapping.model.Property;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "WeatherSensorsMetrics", path = "weatherSensorsMetrics")
public interface WeatherSensorMetricsRepository extends MongoRepository<WeatherSensorMetrics, Long> {

    List<WeatherSensorMetrics> findAllBySensorId(Long sensorId);

    List<WeatherSensorMetrics> findAllBySensorIdAndTimestampGreaterThanOrderByTimestampDesc(Long sensorId,
                                                                                          long timestamp);

    List<WeatherSensorMetrics> findWeatherSensorMetricsByTimestampIsBetween(Long sensorId, long timestamp1,
                                                                            long timestamp2);
    //get average temperature and humidity for a sensor based on the sensorId
    @Aggregation(pipeline = {
            "{ $match: { sensorId: { $eq: ?0} }}",
            "{ $group: { _id: '$sensorId', avgTemperature: { $avg: '$temperature' }, avgHumidity: { $avg: '$humidity' } } }"
    })
    List<JSONObject> getAverageTemperatureAndHumidity(Long sensorId);
}

