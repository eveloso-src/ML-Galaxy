package com.ml.rest;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    //private static final String template = "%s!";
    

    @RequestMapping("/clima")
    public Forecast clima(@RequestParam(value="dia", defaultValue="") Integer dayNumber) {
        return new Forecast(dayNumber );
    }
}
