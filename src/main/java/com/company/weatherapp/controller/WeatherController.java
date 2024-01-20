package com.company.weatherapp.controller;

import com.company.weatherapp.entity.CurrentCity;
import com.company.weatherapp.entity.WeatherINFO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;


@Controller
@RequestMapping("/")
public class WeatherController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/index")
    public String index(){

        return "home";
    }

    //    @GetMapping("test")
    @PostMapping("/process-current-weather")
    public String currentWeather(String city,Model model) {

        // http GET request to open weather URL
        String urlCity = "http://api.openweathermap.org/geo/1.0/direct?q=" + city + "&appid=9029b22dd95a3d31ef1dbef25ee838ee";

        // map request to java object
        CurrentCity[] currentCity = restTemplate.getForObject(urlCity, CurrentCity[].class);

        // obtain entered city data
        double lat = currentCity[0].getLat();

        double lon = currentCity[0].getLon();

        String state = currentCity[0].getState();

        String country = currentCity[0].getCountry();

        String urlWeather = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=9029b22dd95a3d31ef1dbef25ee838ee";


        // get weather info mapped object
        WeatherINFO weatherINFO = restTemplate.getForObject(urlWeather, WeatherINFO.class);

        // main weather condition
        String mainWeather = weatherINFO.getWeatherList().get(0).getMain();

        // main weather description
        String description = weatherINFO.getWeatherList().get(0).getDescription();

        // img url
        String iconId = weatherINFO.getWeatherList().get(0).getIconId();

        // Construye la URL de la imagen
        String imageUrl = "http://openweathermap.org/img/w/" + iconId + ".png";

        double temperature = weatherINFO.getCurrent().getTemperature();

        double feelsLike = weatherINFO.getCurrent().getFeelsLike();

        double tempMin = weatherINFO.getCurrent().getTempMin();

        double tempMax = weatherINFO.getCurrent().getTempMax();

        double pressure = weatherINFO.getCurrent().getPressure();

        double humidity = weatherINFO.getCurrent().getHumidity();

        // format weather data
        // temp from kelvin to Celsius
        temperature = temperature - 273.15;
        // format temp to 2 floats
        String formatTemp = String.format("%.2f", temperature);

        // format feels like
        feelsLike = feelsLike - 273.15;
        // format feels like to 2 floats
        String formatfeel = String.format("%.2f", feelsLike);

        // format temp min
        tempMin = tempMin - 273.15;
        // format temp min to 2 floats
        String formatTempMin = String.format("%.2f", tempMin);

        // format temp max
        tempMax = tempMax - 273.15;
        // format temp max to 2 floats
        String formatTempMax = String.format("%.2f", tempMax);

        // add formatted data to model
        model.addAttribute("mainWeather",mainWeather);

        model.addAttribute("description",description);

        model.addAttribute("weatherUrl",imageUrl);

        model.addAttribute("city",city);

        model.addAttribute("country",country);

        model.addAttribute("state",state);

        model.addAttribute("temperature",formatTemp);

        model.addAttribute("feelsLike",formatfeel);

        model.addAttribute("tempMin",formatTempMin);

        model.addAttribute("tempMax",formatTempMax);

        model.addAttribute("pressure",pressure);

        model.addAttribute("humidity",humidity);

        return "weather-info";
    }
}
