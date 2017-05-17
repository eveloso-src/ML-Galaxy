package rest;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.ml.rest.model.DayForecast;
import com.ml.rest.service.DayForecastService;


@RunWith(MockitoJUnitRunner.class)
public class ForecastTest {
	

	static Logger log = Logger.getLogger(ForecastTest.class.getName());
	
	DayForecastService forecastService = new DayForecastService();

	@Test
	public void testPeriodos() {

		int contadorPeriodos = forecastService.getCantidadPeriodos( "lluvia");
    	log.info("cantidad periodos " + "lluvia" + ": " + contadorPeriodos);
    	
		int contadorPeriodoss = forecastService.getCantidadPeriodos( "sequia");
    	log.info("cantidad periodos sequia" + ": " + contadorPeriodoss);    	
    	
		int contadorPeriodoso = forecastService.getCantidadPeriodos( "optimo");
    	log.info("cantidad periodos optimo" + ": " + contadorPeriodoso);        	
	}

	
}
