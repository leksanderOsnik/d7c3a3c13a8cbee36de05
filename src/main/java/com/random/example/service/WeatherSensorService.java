package com.random.example.service;

import com.random.example.entity.WeatherSensor;
import com.random.example.entity.WeatherSensorMetrics;
import net.minidev.json.JSONObject;
import org.springframework.data.mapping.model.Property;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface WeatherSensorService {

    public void addWeatherSensor(WeatherSensor weatherSensor);

    public void removeWeatherSensor(Long id);

    public void addWeatherSensorMetrics(Long sensorId, WeatherSensorMetrics metrics);

    public List<WeatherSensor> getAllWeatherSensors();

    public WeatherSensor getOneWeatherSensor(Long id);

    public List<WeatherSensorMetrics> getOneWeatherSensorMetrics(Long Id);

    public List<WeatherSensorMetrics> getOneWeatherSensorMetricsInTimePeriod(Long id, Long days);

    public List<JSONObject> getAverageTemperatureAndHumidity(Long sensorId);



}
