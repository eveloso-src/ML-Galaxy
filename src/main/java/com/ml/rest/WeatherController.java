package com.ml.rest;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ml.rest.model.DayForecast;
import com.ml.rest.service.DayForecastService;

@RestController
public class WeatherController {
	static Logger log = Logger.getLogger(WeatherController.class.getName());    

	@Autowired
	DayForecastService forecastService;
	
    @RequestMapping("/clima")
    public DayForecast clima(@RequestParam(value="dia", defaultValue="1") Integer dayNumber) {
        return forecastService.getDayForecast(dayNumber);
    }
    
    @RequestMapping("/periodos")
    public int periodos(@RequestParam(value="clima", defaultValue="lluvia") String tipoClima) {
    
    
    	
    	int contadorPeriodos = getCantidadPeriodos( "lluvia");
    	log.info("cantidad periodos " + "lluvia" + ": " + contadorPeriodos);
    	
		int contadorPeriodoss = getCantidadPeriodos( "sequia");
    	log.info("cantidad periodos sequia" + ": " + contadorPeriodoss);    	
    	
		int contadorPeriodoso = getCantidadPeriodos( "optimo");
    	log.info("cantidad periodos optimo" + ": " + contadorPeriodoso);   
    	
    	contadorPeriodos = getCantidadPeriodos(tipoClima);
    	
    	return contadorPeriodos;
    }

    private int getCantidadPeriodos(String climaPeriodo) {
		DayForecast fcstAux = new DayForecast();
		DayForecast fcst; 
		int contadorPeriodos = 0;
    	boolean nuevoPeriodo = true;
    	for(int i=0; i < 3650 ; i++) {
    		fcstAux.setDia(i);	
    		fcst = forecastService.getDayForecast(i);
    		
    		if (fcstAux != null && fcstAux.getClima()!=null && !fcstAux.getClima().equals(fcst.getClima()) && fcst.getClima().equals(climaPeriodo) && nuevoPeriodo) {
    			//clima = fcst.getClima();
    			contadorPeriodos++;
    			nuevoPeriodo = false;
    			//log.info("sumar periodo " + i);
    		}
    		else {
    			nuevoPeriodo = true;
    		}
    		fcstAux.setClima(fcst.getClima());
    	}
		return contadorPeriodos;
	}
}
