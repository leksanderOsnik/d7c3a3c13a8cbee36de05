package com.random.example;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "WeatherSensors")
public class WeatherSensor {

    @Id
    private Long id;
    private String country;
    private String city;

    public WeatherSensor(Long id, String country, String city){
        this.id = id;
        this.country = country;
        this.city = city;

    }

    public Long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }


}
