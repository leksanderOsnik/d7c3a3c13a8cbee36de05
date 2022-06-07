package com.random.example.controller;

import com.random.example.entity.WeatherSensor;
import com.random.example.entity.WeatherSensorMetrics;
import com.random.example.repository.WeatherSensorMetricsRepository;
import com.random.example.repository.WeatherSensorRepository;
import com.random.example.service.WeatherSensorService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.model.Property;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;

@Component
@RestController("WeatherSensorController")
public class WeatherSensorController {

    @Autowired
    private WeatherSensorRepository weatherSensorRepository;
    @Autowired
    private WeatherSensorMetricsRepository metricsRepository;

    @Autowired
    private WeatherSensorService weatherSensorService;

    @GetMapping(value = "/weatherSensors")
    public ResponseEntity<List<WeatherSensor>> getAllWeatherSensors() {
        return ResponseEntity.ok(weatherSensorService.getAllWeatherSensors());
    }

    @GetMapping(value = "/weatherSensors/{id}/metrics")
    public ResponseEntity<List<WeatherSensorMetrics>> getOneWeatherSensorMetrics(@PathVariable("id") Long id) {
        return ResponseEntity.ok(weatherSensorService.getOneWeatherSensorMetrics(id));
    }

    @GetMapping(value = "/weatherSensors/{id}/metrics/{days}")
    public ResponseEntity<List<WeatherSensorMetrics>> getOneWeatherSensorMetricsInTimePeriod(@PathVariable("id")Long id, @PathVariable("days") Long days) {
        return ResponseEntity.ok(weatherSensorService.getOneWeatherSensorMetricsInTimePeriod(id, days));
    }

    @RequestMapping(value = "/addSensor/{id}/{country}/{city}", method = RequestMethod.POST)
    public void addWeatherSensor(@PathVariable("id") Long id, @PathVariable("country") String country, @PathVariable("city") String city) {
        weatherSensorService.addWeatherSensor(new WeatherSensor(id, country, city));
    }

    @RequestMapping(value = "/addMetrics/{sensorId}/{id}/{temperature}/{humidity}", method = RequestMethod.POST)
    public void addWeatherSensorMetrics(@PathVariable("sensorId") Long sensorId, @PathVariable("id") Long id,
                                        @PathVariable("temperature") double temperature,
                                        @PathVariable("humidity") double humidity) {
        weatherSensorService.addWeatherSensorMetrics(sensorId, new WeatherSensorMetrics(id, temperature,
                humidity));
    }

    @RequestMapping(value = "/removeSensor/{id}", method = RequestMethod.DELETE)
    public void removeWeatherSensor(@PathVariable("id") Long id) {
        weatherSensorService.removeWeatherSensor(id);
    }

    @RequestMapping(value = "/weatherSensors/{id}/metrics/average", method = RequestMethod.GET)
    public ResponseEntity<List<JSONObject>> getAverageTemperature(@PathVariable("id") Long id) {
        return ResponseEntity.ok(weatherSensorService.getAverageTemperatureAndHumidity(id));
    }






    //Method used only for database population
//    @RequestMapping(value = "/createData")
//    public void  createData() {
//        weatherSensorRepository.save(new WeatherSensor(1L, "London", "UK"));
//        weatherSensorRepository.save(new WeatherSensor(2L, "Paris", "France"));
//        weatherSensorRepository.save(new WeatherSensor(3L, "New York", "USA"));
//
//        var m1 = new WeatherSensorMetrics(13L,  10.0, 20.0);
//        var m2 = new WeatherSensorMetrics(14L,  15.0, 28.0);
//        var m3 = new WeatherSensorMetrics(15L,  15.0, 23.0);
//        var m4 = new WeatherSensorMetrics(16L,  10.0, 20.0);
//        var m5 = new WeatherSensorMetrics(17L,  15.0, 28.0);
//        var m6 = new WeatherSensorMetrics(18L,  25.0, 74.0);
//
//        m1.setSensorId(1L);
//        m2.setSensorId(1L);
//        m3.setSensorId(2L);
//        m4.setSensorId(2L);
//        m5.setSensorId(3L);
//        m6.setSensorId(3L);
//
//        m1.setTimestamp(LocalDate.now().minusDays(25).toEpochSecond(LocalTime.now(), ZoneOffset.UTC));
//        m2.setTimestamp(LocalDate.now().minusDays(24).toEpochSecond(LocalTime.now(), ZoneOffset.UTC));
//        m3.setTimestamp(LocalDate.now().minusDays(7).toEpochSecond(LocalTime.now(), ZoneOffset.UTC));
//        m4.setTimestamp(LocalDate.now().minusDays(5).toEpochSecond(LocalTime.now(), ZoneOffset.UTC));
//        m5.setTimestamp(LocalDate.now().minusDays(3).toEpochSecond(LocalTime.now(), ZoneOffset.UTC));
//        m6.setTimestamp(LocalDate.now().minusDays(2).toEpochSecond(LocalTime.now(), ZoneOffset.UTC));
//
//        metricsRepository.save(m1);
//        metricsRepository.save(m2);
//        metricsRepository.save(m3);
//        metricsRepository.save(m4);
//        metricsRepository.save(m5);
//        metricsRepository.save(m6);
//    }

}
