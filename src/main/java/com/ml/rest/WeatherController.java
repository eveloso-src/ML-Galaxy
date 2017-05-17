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
        return forecastService.getDayForecast(dayNumber);
    }
    
    @RequestMapping("/periodos")
    public void periodos() {
    
    
    	getClimaPeriodos("lluvia");
    }

	public int getClimaPeriodos(String climaPeriodo) {
		DayForecast fcstAux = new DayForecast();
		DayForecast fcst; 
    	
    	String clima;
    	int contadorPeriodos = 0;
    	for(int i=0; i < 36000 ; i++) {
    		fcstAux.setDia(i);	
    		fcst = forecastService.getDayForecast(i);
    		
    		if (fcstAux != null && !fcstAux.getClima().equals(fcst.getClima()) && fcst.getClima().equals(climaPeriodo)) {
    			clima = fcst.getClima();
    			contadorPeriodos++;
    		}
    		fcstAux.setClima(fcst.getClima());
    	}
		
    	return contadorPeriodos;
	}
}
