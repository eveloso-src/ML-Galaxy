package com.ml.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ml.rest.model.DayForecast;
import com.ml.rest.service.DayForecastService;

@RestController
public class WeatherController {

    //private static final String template = "%s!";
    

	@Autowired
	DayForecastService forecastService;
	
    @RequestMapping("/clima")
    public DayForecast clima(@RequestParam(value="dia", defaultValue="1") Integer dayNumber) {
        return new DayForecast(dayNumber );
    }
    
    @RequestMapping("/periodos")
    public void periodos() {
    	DayForecast fcst; 
    	DayForecast fcstAux = null;
    	
    	for(int i=0; i < 30000 ; i++) {
    		fcst = new DayForecast(i);
    		fcst.setClima(forecastService.getDayForecast(i));
    		if (fcstAux != null && fcstAux.getClima().equals(fcst.getClima())) {
    			
    		}
    		
    	}
    	
    	
        //return new DayForecast(dayNumber );
    }
    
  
}
