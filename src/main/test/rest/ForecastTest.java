package rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import org.springframework.context.annotation.ComponentScan;
import org.apache.log4j.Logger;
import com.ml.rest.model.DayForecast;
import com.ml.rest.service.DayForecastService;


@RunWith(MockitoJUnitRunner.class)
@ComponentScan(basePackages = {"com.ml.rest.service"})
public class ForecastTest {
	
//	private static final Logger log = LoggerFactory.getLogger(ForecastTest.class);
	static Logger log = Logger.getLogger(ForecastTest.class.getName());
	   
	

	
	DayForecastService forecastService = new DayForecastService();

	@Test
	public void testPeriodos() {

		int contadorPeriodos = getCantidadPeriodos( "lluvia");
    	log.info("cantidad periodos " + "lluvia" + ": " + contadorPeriodos);
    	
		int contadorPeriodoss = getCantidadPeriodos( "sequia");
    	log.info("cantidad periodos sequia" + ": " + contadorPeriodoss);    	
    	
		int contadorPeriodoso = getCantidadPeriodos( "optimo");
    	log.info("cantidad periodos optimo" + ": " + contadorPeriodoso);        	
	}

	private int getCantidadPeriodos(String climaPeriodo) {
		DayForecast fcstAux = new DayForecast();
		DayForecast fcst; 
		int contadorPeriodos = 0;
    	boolean nuevoPeriodo = true;
    	for(int i=0; i < 3600 ; i++) {
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
