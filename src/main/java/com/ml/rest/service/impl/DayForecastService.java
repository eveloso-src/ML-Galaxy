package com.ml.rest.service.impl;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.ml.rest.model.DayForecast;
import com.ml.rest.model.Line;
import com.ml.rest.model.PlanetBase;
import com.ml.rest.model.PlanetBetasoide;
import com.ml.rest.model.PlanetFerengi;
import com.ml.rest.model.PlanetVulcano;
import com.ml.rest.model.Position;
import com.ml.rest.service.IDayForecastService;
import com.ml.rest.util.PlanetFactory;
import com.ml.rest.util.PositionYComparator;


@Component("forecastService")

public class DayForecastService implements IDayForecastService{
	
	private static final double ZERO = 0;
	
	private static final Log log = LogFactory.getLog(DayForecastService.class);
	
	private GeometricService geoService = new GeometricService();
	
	public DayForecast getDayForecast(int dia) {
		
		String clima = DayForecast.WEATHER_DRY;

		PlanetBetasoide pbeta = (PlanetBetasoide) PlanetFactory.getPlanet(PlanetBase.PLANET_BETA);
		PlanetFerengi pfere = (PlanetFerengi) PlanetFactory.getPlanet(PlanetBase.PLANET_FERE);
		PlanetVulcano pvul = (PlanetVulcano) PlanetFactory.getPlanet(PlanetBase.PLANET_VULC);


		// get positions
		Position posBeta = pbeta.getPosition(pbeta.getOffsetInGrades(dia) );

		Position posFere = pfere.getPosition( pfere.getOffsetInGrades(dia) );

		Position posVul = pvul.getPosition(pvul.getOffsetInGrades(dia) );



		// Sun line
		if (isSunLine(posBeta, posFere, posVul)) {
			clima = DayForecast.WEATHER_RAIN;
		} else {
			
			
			// order
			java.util.List<Position> positionYOrder = new ArrayList<Position>();
			positionYOrder.add(posFere);
			positionYOrder.add(posBeta);
			positionYOrder.add(posVul);

			Collections.sort(positionYOrder, new PositionYComparator());

			Position posYMax = positionYOrder.get(2);
			Position posYMed = positionYOrder.get(1);
			Position posYMin = positionYOrder.get(0);
			
			if (isTriangle(posYMax, posYMed, posYMin)) {
				
				clima = DayForecast.WEATHER_RAIN;
			
			} else {
				// Line up
				double yValueMaxMed = geoService.getYvalueForX(posYMax, posYMed, ZERO);

				double yValueMaxMin = geoService.getYvalueForX(posYMax, posYMin, ZERO);

				if (yValueMaxMed == yValueMaxMin && yValueMaxMed != ZERO) {
					clima = DayForecast.WEATHER_OPTIMUS;
				}
			}

		}

		return new DayForecast(dia, clima);
	}
		
	private boolean isTriangle(Position posYMax, Position posYMed, Position posYMin) {

		// condition y
		if (posYMax.getAxisY() > ZERO && posYMin.getAxisX() < ZERO) {

			// condition x
			if (
			// different side y
			(posYMax.getAxisX() > ZERO && posYMin.getAxisX() < ZERO) ||

			// different opposite side y
					(posYMax.getAxisX() < ZERO && posYMin.getAxisX() > ZERO) ||

					// same side y but mid different side
					(posYMax.getAxisX() < ZERO && posYMed.getAxisX() > ZERO) ||

					// same side y but mid different opposite side
					(posYMax.getAxisX() > 0 && posYMed.getAxisX() < 0)

			) {

				double yValueMaxMin = geoService.getYvalueForX(posYMax, posYMin, 0);
				double yValueMaxMed = geoService.getYvalueForX(posYMax, posYMed, 0);

				// Position y0-min, y0-med > 0
				if ((yValueMaxMin > 0 && yValueMaxMed < 0) || yValueMaxMin < 0 && yValueMaxMed > 0) {

					return true;
				}
			}
		}
		return false;

	}

	private boolean isSunLine(Position posBeta, Position posFere, Position posVul) {
		boolean horizontal = posBeta.getAxisY() == posFere.getAxisY() && posFere.getAxisY() == posVul.getAxisY()
				&& (posVul.getAxisX() == 90 || posVul.getAxisX() == 270);
		boolean vertical = posBeta.getAxisX() == posFere.getAxisX() && posFere.getAxisX() == posVul.getAxisX()
				&& (posVul.getAxisX() == ZERO || posVul.getAxisX() == 180);

		double yValue = geoService.getYvalueForX(posBeta, posFere, ZERO);

		double yValue2 = geoService.getYvalueForX(posVul, posFere, ZERO);

		// double yValue3 = getYvalueForX(posVul, posBeta, 0);

		boolean diagonal = yValue == ZERO && yValue2 == ZERO;

		return horizontal || vertical || diagonal;
	}

	public int getPeriodAmount(String climaPeriodo) {
		DayForecast fcstAux = new DayForecast();
		DayForecast fcst; 
		int contadorPeriodos = 0;
    	boolean nuevoPeriodo = true;
    	double maxLength = 0;
    	int maxDay = 0;
    	double currentLength;
		for (int i = 0; i < 3650; i++) {
    		fcstAux.setDia(i);	
    		fcst = getDayForecast(i);
    		
    		if (fcstAux != null && fcstAux.getClima()!=null && !fcstAux.getClima().equals(fcst.getClima()) && fcst.getClima().equals(climaPeriodo) && nuevoPeriodo) {
    			
    			contadorPeriodos++;
    			nuevoPeriodo = false;
    			
    			if (DayForecast.WEATHER_RAIN.equals(fcst.getClima()) ) {
    				java.util.List<Line> lines = new ArrayList<Line>();
    				
    				PlanetBetasoide pbeta = (PlanetBetasoide) PlanetFactory.getPlanet(PlanetBase.PLANET_BETA);
    				PlanetFerengi pfere = (PlanetFerengi) PlanetFactory.getPlanet(PlanetBase.PLANET_FERE);
    				PlanetVulcano pvul = (PlanetVulcano) PlanetFactory.getPlanet(PlanetBase.PLANET_VULC);


    				// get positions
    				Position posBeta = pbeta.getPosition(pbeta.getOffsetInGrades(i) );
    				Position posFere = pfere.getPosition( pfere.getOffsetInGrades(i) );
    				Position posVul = pvul.getPosition(pvul.getOffsetInGrades(i) );
    				
    				lines.add(new Line(posBeta, posFere));
    				lines.add(new Line(posBeta, posVul));
    				lines.add(new Line(posFere, posVul));
    				
    				currentLength = geoService.getPerimeter(lines );
    				if (maxLength < currentLength) {
    					maxLength = currentLength;
    					maxDay = i;
    				}
    			}

    		}
    		else {
    			nuevoPeriodo = true;
    		}
    		fcstAux.setClima(fcst.getClima());
    	}
		
		if (DayForecast.WEATHER_RAIN.equals(climaPeriodo) ) {
			log.info("Maxima cantidad lluvias dia: " + maxDay);
			
		}
		return contadorPeriodos;
	}	

}
