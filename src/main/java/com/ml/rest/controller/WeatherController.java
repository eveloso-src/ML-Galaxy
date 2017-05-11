package com.ml.rest.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ml.rest.Forecast;

@RestController
public class WeatherController {

    //private static final String template = "%s!";
    

    @RequestMapping("/clima")
    public Forecast clima(@RequestParam(value="dia", defaultValue="1") Integer dayNumber) {
        return new Forecast(dayNumber );
    }
}