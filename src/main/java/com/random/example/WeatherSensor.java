package com.random.example;

import org.springframework.data.annotation.Id;


import javax.annotation.processing.Generated;

public class WeatherSensor {

    @Id
    private String id;

    private String sensorName;
    private String country;
    private String city;

    public WeatherSensor(String id, String country, String city){
        this.id = id;
        this.country = country;
        this.city = city;

    }

    public String getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }


}
