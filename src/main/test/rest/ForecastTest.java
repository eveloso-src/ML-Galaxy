package rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;

import com.ml.rest.model.DayForecast;
import com.ml.rest.service.DayForecastService;


@RunWith(MockitoJUnitRunner.class)
@ComponentScan(basePackages = {"com.ml.rest.service"})
public class ForecastTest {
	
	private static final Logger log = LoggerFactory.getLogger(ForecastTest.class);
	

	
	DayForecastService forecastService = new DayForecastService();

	@Test
	public void testForecast() {
		DayForecast fcst;
		DayForecast fcstAux = null;
		log.info("Comienzo");

		for (int i = 0; i < 181; i++) {
			
    		fcst = forecastService.getDayForecast(i);
			log.info("#" + i + " " + fcst.getClima());
			log.info("--------------------------");

			if (fcstAux != null && fcstAux.getClima().equals(fcst.getClima())) {

			}

		}
	}
}
