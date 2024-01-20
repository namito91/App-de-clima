package com.company.weatherapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {

    @JsonProperty("main")
    private String main;

    @JsonProperty("description")
    private String description;

    @JsonProperty("icon")
    private String iconId;

    public Weather(){}

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIconId() {
        return iconId;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIconId(String iconId) {
        this.iconId = iconId;
    }
}
