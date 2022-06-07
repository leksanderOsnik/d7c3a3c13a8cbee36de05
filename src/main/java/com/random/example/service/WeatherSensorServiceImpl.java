package com.random.example;

import com.random.example.WeatherSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;

@Service("WeatherSensorService")
public class WeatherSensorServiceImpl implements WeatherSensorService {

    @Autowired
    private WeatherSensorRepository repository;

    @Autowired
    private WeatherSensorMetricsRepository metricsRepository;
    @Override
    public void addWeatherSensor(WeatherSensor weatherSensor) {
        repository.save(weatherSensor);
    }

    @Override
    public void removeWeatherSensor(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void addWeatherSensorMetrics(Long sensorId, WeatherSensorMetrics metrics) {
        metrics.setSensorId(sensorId);
        metrics.setTimestamp(LocalDate.now().toEpochSecond(LocalTime.now(), ZoneOffset.UTC));
        metricsRepository.save(metrics);
    }

    @Override
    public void getAllWeatherSensors() {
        repository.findAll();
    }

    @Override
    public void getOneWeatherSensor(Long id) {
        repository.findById(id);
    }

    @Override
    public void getAllWeatherSensorsMetrics() {
        metricsRepository.findAll();
    }

    @Override
    public void getOneWeatherSensorMetricsInTimePeriod(Long id, long days) {
        var timestamp = LocalDate.now().minusDays(days).toEpochSecond(LocalTime.now(), ZoneOffset.UTC);
        metricsRepository.findAllBySensorIdAndTimestampGreaterThanOrderByTimestampDesc(id, timestamp);
    }
}
