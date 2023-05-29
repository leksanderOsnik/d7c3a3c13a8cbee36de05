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
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RestController("WeatherSensorController")
@RequestMapping("/api")
public class WeatherSensorController {

    @Autowired
    private WeatherSensorRepository weatherSensorRepository;
    @Autowired
    private WeatherSensorMetricsRepository metricsRepository;

    @Autowired
    private WeatherSensorService weatherSensorService;

    @GetMapping("/weatherSensors")
    public ResponseEntity<List<WeatherSensor>> getAllWeatherSensors() {
        return ResponseEntity.ok(weatherSensorService.getAllWeatherSensors());
    }

    @GetMapping("/sensor")
    public ResponseEntity<List<WeatherSensorMetrics>> getOneWeatherSensorMetrics(@RequestParam("id") Long id) {
        return ResponseEntity.ok(weatherSensorService.getOneWeatherSensorMetrics(id));
    }

    @GetMapping(value = "/sensorInTimePeriod")
    public ResponseEntity<List<WeatherSensorMetrics>> getOneWeatherSensorMetricsInTimePeriod(@RequestParam("id")Long id,
                                                                                             @RequestParam("days") Long days) {
        return ResponseEntity.ok(weatherSensorService.getOneWeatherSensorMetricsInTimePeriod(id, days));
    }

    @PostMapping(value = "/addSensor")
    public void addWeatherSensor(@RequestParam("id") Long id, @RequestParam("country") String country,
                                 @RequestParam("city") String city) {
        weatherSensorService.addWeatherSensor(new WeatherSensor(id, country, city));
    }

    @PostMapping(value = "/addMetrics")
    public void addWeatherSensorMetrics(@RequestParam("sensorId") Long sensorId, @RequestParam("id") Long id,
                                        @RequestParam("temperature") double temperature,
                                        @RequestParam("humidity") double humidity) {
        weatherSensorService.addWeatherSensorMetrics(sensorId, new WeatherSensorMetrics(id, temperature,
                humidity));
    }

    @DeleteMapping(value = "/removeSensor")
    public void removeWeatherSensor(@RequestParam("id") Long id) {
        weatherSensorService.removeWeatherSensor(id);
    }

    @GetMapping(value = "/average")
    public ResponseEntity<List<JSONObject>> getAverageTemperature(@RequestParam("id") Long id) {
        return ResponseEntity.ok(weatherSensorService.getAverageTemperatureAndHumidity(id));
    }






    //Method used only for database population
//    @RequestMapping(value = "/createData")
//    public void  createData() {
//        weatherSensorRepository.save(new WeatherSensor(1L, "London", "UK"));
//        weatherSensorRepository.save(new WeatherSensor(2L, "Paris", "France"));
//        weatherSensorRepository.save(new WeatherSensor(3L, "New York", "USA"));
//        Random rand = new Random();
//        AtomicInteger id = new AtomicInteger(1);
//        for(var i = 0; i<100; i++) {
//            var m1 = new WeatherSensorMetrics(id.longValue(), 5 + rand.nextFloat() * (30-5), 40 + rand.nextFloat() * (70-40));
//            var m2 = new WeatherSensorMetrics(id.longValue(), 5 + rand.nextFloat() * (30-5), 40 + rand.nextFloat() * (70-40));
//            var m3 = new WeatherSensorMetrics(id.longValue(), 5 + rand.nextFloat() * (30-5), 40 + rand.nextFloat() * (70-40));
//
//            m1.setSensorId(1L);
//            m2.setSensorId(2L);
//            m3.setSensorId(3L);
//
//            m1.setTimestamp(LocalDate.now().minusDays(rand.nextInt()).toEpochSecond(LocalTime.now(), ZoneOffset.UTC));
//            m2.setTimestamp(LocalDate.now().minusDays(rand.nextInt()).toEpochSecond(LocalTime.now(), ZoneOffset.UTC));
//            m3.setTimestamp(LocalDate.now().minusDays(rand.nextInt()).toEpochSecond(LocalTime.now(), ZoneOffset.UTC));
//
//            metricsRepository.save(m1);
//            metricsRepository.save(m2);
//            metricsRepository.save(m3);
//        }
//
//
//    }

}
