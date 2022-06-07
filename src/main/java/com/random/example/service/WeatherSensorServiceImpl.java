package com.random.example.service;

import com.random.example.entity.WeatherSensor;
import com.random.example.entity.WeatherSensorMetrics;
import com.random.example.repository.WeatherSensorMetricsRepository;
import com.random.example.repository.WeatherSensorRepository;
import net.minidev.json.JSONObject;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.model.Property;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.NoSuchElementException;

@Service
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
        repository.findById(sensorId).ifPresentOrElse(sensor -> {
            metrics.setSensorId(sensorId);
            metrics.setTimestamp(LocalDate.now().toEpochSecond(LocalTime.now(), ZoneOffset.UTC));
            metricsRepository.save(metrics);
        }, () -> {
            throw new NoSuchElementException("Sensor with id " + sensorId + " not found");
        });

    }

    @Override
    public List<WeatherSensor> getAllWeatherSensors() {
       return repository.findAll();
    }

    @Override
    public WeatherSensor getOneWeatherSensor(Long id) {
         return repository.findById(id).orElseThrow(() -> new NoSuchElementException("No such element"));
    }

    @Override
    public List<WeatherSensorMetrics> getOneWeatherSensorMetrics(Long Id) {
        return metricsRepository.findAllBySensorId(Id);
    }

    @Override
    public List<WeatherSensorMetrics> getOneWeatherSensorMetricsInTimePeriod(Long id, Long days) {
        var timestamp = LocalDate.now().minusDays(days).toEpochSecond(LocalTime.now(), ZoneOffset.UTC);
        return metricsRepository.findAllBySensorIdAndTimestampGreaterThanOrderByTimestampDesc(id, timestamp);

    }

    public List<JSONObject> getAverageTemperatureAndHumidity(Long sensorId){
        return metricsRepository.getAverageTemperatureAndHumidity(sensorId);
    }
}
