package rest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ml.rest.model.DayForecast;


public class ForecastTest {
	
	private static final Logger log = LoggerFactory.getLogger(ForecastTest.class);

	@Test
	public void testForecast() {
		DayForecast fcst;
		DayForecast fcstAux = null;
		log.info("Comienzo");

		for (int i = 0; i < 30; i++) {
			fcst = new DayForecast(i);
			log.info("#" + i + " " + fcst.getClima());
			log.info("--------------------------");

			if (fcstAux != null && fcstAux.getClima().equals(fcst.getClima())) {

			}

		}
	}
}
