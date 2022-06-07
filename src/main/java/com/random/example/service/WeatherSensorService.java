package com.random.example;

public interface WeatherSensorService {

    public void addWeatherSensor(WeatherSensor weatherSensor);

    public void removeWeatherSensor(Long id);

    public void addWeatherSensorMetrics(Long sensorId, WeatherSensorMetrics metrics);

    public void getAllWeatherSensors();

    public void getOneWeatherSensor(Long id);

    public void getAllWeatherSensorsMetrics();

    public void getOneWeatherSensorMetricsInTimePeriod(Long id, long days);



}
