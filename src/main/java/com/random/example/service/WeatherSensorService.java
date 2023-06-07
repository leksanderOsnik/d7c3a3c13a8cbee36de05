package com.random.example.service;

import com.random.example.entity.WeatherSensor;
import com.random.example.entity.WeatherSensorMetrics;
import net.minidev.json.JSONObject;
import org.springframework.data.mapping.model.Property;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface WeatherSensorService {

     void addWeatherSensor(WeatherSensor weatherSensor);

     void removeWeatherSensor(Long id);

     void addWeatherSensorMetrics(Long sensorId, WeatherSensorMetrics metrics);

     List<WeatherSensor> getAllWeatherSensors();

     WeatherSensor getOneWeatherSensor(Long id);

     List<WeatherSensorMetrics> getOneWeatherSensorMetrics(Long Id);

     List<WeatherSensorMetrics> getOneWeatherSensorMetricsInTimePeriod(Long id, Long days);

     List<JSONObject> getAverageTemperatureAndHumidity(Long sensorId);

     List<WeatherSensorMetrics> getSensorMetricsBetweenDates(Long id, String timestamp1, String timestamp2);



}
