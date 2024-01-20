package com.company.weatherapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherINFO {

    @JsonProperty("main")
    private MainWeatherInfo main;

    @JsonProperty("weather")
    private List<Weather> weatherList;


    public WeatherINFO(){};

    public MainWeatherInfo getCurrent() {
        return main;
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public void setCurrent(MainWeatherInfo main) {
        this.main = main;
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }
}
