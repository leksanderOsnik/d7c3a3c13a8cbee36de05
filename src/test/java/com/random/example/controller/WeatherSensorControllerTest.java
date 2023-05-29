package com.random.example.controller;

import com.random.example.entity.WeatherSensor;
import com.random.example.entity.WeatherSensorMetrics;
import com.random.example.repository.WeatherSensorMetricsRepository;
import com.random.example.repository.WeatherSensorRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.NoSuchElementException;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@AutoConfigureDataMongo
@AutoConfigureMockMvc
@DataMongoTest(properties = {"spring.mongodb.embedded.version=4.0.21"})
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WeatherSensorControllerTest {


    @Autowired
    private WeatherSensorController weatherSensorController;
    @Autowired
    private WeatherSensorRepository weatherSensorRepository;

    @Autowired
    private WeatherSensorMetricsRepository metricsRepository;


    @BeforeAll
    public void setup() {
        weatherSensorRepository.save(new WeatherSensor(1L, "UK", "London"));
        weatherSensorRepository.save(new WeatherSensor(2L, "France", "Paris"));
        weatherSensorRepository.save(new WeatherSensor(3L, "USA", "New York"));

        var m1 = new WeatherSensorMetrics(13L,  15.0, 23.0);
        var m2 = new WeatherSensorMetrics(14L,  15.0, 28.0);
        var m3 = new WeatherSensorMetrics(15L,  15.0, 23.0);
        var m4 = new WeatherSensorMetrics(16L,  15.0, 23.0);
        var m5 = new WeatherSensorMetrics(17L,  15.0, 28.0);
        var m6 = new WeatherSensorMetrics(18L,  25.0, 74.0);

        m1.setSensorId(1L);
        m2.setSensorId(1L);
        m3.setSensorId(2L);
        m4.setSensorId(2L);
        m5.setSensorId(3L);
        m6.setSensorId(3L);

        m1.setTimestamp(LocalDate.now().minusDays(25).toEpochSecond(LocalTime.now(), ZoneOffset.UTC));
        m2.setTimestamp(LocalDate.now().minusDays(24).toEpochSecond(LocalTime.now(), ZoneOffset.UTC));
        m3.setTimestamp(LocalDate.now().minusDays(7).toEpochSecond(LocalTime.now(), ZoneOffset.UTC));
        m4.setTimestamp(LocalDate.now().minusDays(5).toEpochSecond(LocalTime.now(), ZoneOffset.UTC));
        m5.setTimestamp(LocalDate.now().minusDays(3).toEpochSecond(LocalTime.now(), ZoneOffset.UTC));
        m6.setTimestamp(LocalDate.now().minusDays(2).toEpochSecond(LocalTime.now(), ZoneOffset.UTC));

        metricsRepository.save(m1);
        metricsRepository.save(m2);
        metricsRepository.save(m3);
        metricsRepository.save(m4);
        metricsRepository.save(m5);
        metricsRepository.save(m6);

    }

    @Test
    public void testGetAllWeatherSensors() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(weatherSensorController).build();
        mockMvc.perform(get("/api/weatherSensors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].country").value("UK"))
                .andExpect(jsonPath("$.[0].city").value("London"));
    }


    @Test
    public void testGetWeatherSensorInTimePeriod() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(weatherSensorController).build();
        mockMvc.perform(post("/api/addMetrics")
                        .param("sensorId", String.valueOf(2))
                        .param("id",String.valueOf(109))
                        .param("temperature", String.valueOf(10.0))
                        .param("humidity", String.valueOf(20.0)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/sensorInTimePeriod")
                        .param("id", String.valueOf(2))
                        .param("days", String.valueOf(4)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[0].sensorId").value(2))
                .andExpect(jsonPath("$.[0].temperature").value(10.0))
                .andExpect(jsonPath("$.[0].humidity").value(20.0));


    }

    @Test
    public void testAddWeatherSensorMetrics() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(weatherSensorController).build();
        mockMvc.perform(post("/api/addMetrics")
                        .param("sensorId", String.valueOf(1))
                        .param("id",String.valueOf(109))
                        .param("temperature", String.valueOf(15.0))
                        .param("humidity", String.valueOf(23.0)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/sensor").param("id", String.valueOf(1)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[0].sensorId").value(1))
                .andExpect(jsonPath("$.[0].temperature").value(15.0))
                .andExpect(jsonPath("$.[0].humidity").value(23.0));
    }

}


