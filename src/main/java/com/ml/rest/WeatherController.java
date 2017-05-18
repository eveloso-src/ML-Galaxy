package com.ml.rest;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ml.rest.model.DayForecast;
import com.ml.rest.service.impl.DayForecastService;


@RestController
public class WeatherController {
	static Logger log = Logger.getLogger(WeatherController.class.getName());    

	@Autowired
	static DayForecastService forecastService = new DayForecastService();
	
    @RequestMapping("/clima")
    public DayForecast clima(@RequestParam(value="dia", defaultValue="1") Integer dayNumber) {
        return forecastService.getDayForecast(dayNumber);
    }
    
    @RequestMapping("/periodos")
    public int periodos(@RequestParam(value="clima", defaultValue="lluvia") String tipoClima) {
    
    
    	
    	int contadorPeriodos = forecastService.getPeriodAmount( "lluvia");
    	log.info("cantidad periodos " + "lluvia" + ": " + contadorPeriodos);
    	
		int contadorPeriodoss = forecastService.getPeriodAmount( "sequia");
    	log.info("cantidad periodos sequia" + ": " + contadorPeriodoss);    	
    	
		int contadorPeriodoso = forecastService.getPeriodAmount( "optimo");
    	log.info("cantidad periodos optimo" + ": " + contadorPeriodoso);   
    	
    	contadorPeriodos = forecastService.getPeriodAmount(tipoClima);
    	
    	return contadorPeriodos;
    }

    
}
