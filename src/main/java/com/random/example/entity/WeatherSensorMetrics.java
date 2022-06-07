package com.random.example.entity;

import org.springframework.data.annotation.Id;

public class WeatherSensorMetrics {

    @Id
    private Long id;
    private Long sensorId;

    private long timestamp;

    private double temperature;

    private double humidity;

    public WeatherSensorMetrics(Long id, double temperature, double humidity) {
        this.id = id;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }




}
