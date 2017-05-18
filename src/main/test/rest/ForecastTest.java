package rest;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.ml.rest.service.impl.DayForecastService;


@RunWith(MockitoJUnitRunner.class)
public class ForecastTest {
	

	static Logger log = Logger.getLogger(ForecastTest.class.getName());
	
	DayForecastService forecastService = new DayForecastService();

	@Test
	public void testPeriodos() {

		int contadorPeriodos = forecastService.getPeriodAmount( "lluvia");
    	log.info("cantidad periodos " + "lluvia" + ": " + contadorPeriodos);
    	
		int contadorPeriodoss = forecastService.getPeriodAmount( "sequia");
    	log.info("cantidad periodos sequia" + ": " + contadorPeriodoss);    	
    	
		int contadorPeriodoso = forecastService.getPeriodAmount( "optimo");
    	log.info("cantidad periodos optimo" + ": " + contadorPeriodoso);        	
	}

	
}
