package com.ml.rest.service;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.ml.rest.model.DayForecast;
import com.ml.rest.model.PlanetBetasoide;
import com.ml.rest.model.PlanetFerengi;
import com.ml.rest.model.PlanetVulcano;
import com.ml.rest.model.Position;
import com.ml.rest.util.PositionYComparator;


@Component
public class DayForecastService {
	
	private static final Log log = LogFactory.getLog(DayForecastService.class);

	public static final String WEATHER_RAIN = "lluvia";
	public static final String WEATHER_OPTIMUS = "optimo";
	public static final String WEATHER_DRY = "sequia";
	
//	@Bean
//	private PlanetBetasoide pbeta;
//	
//	@Bean	
//	private PlanetFerengi pfere;
//	
//	@Bean	
//	private PlanetVulcano pvul;	
	
	public DayForecast getDayForecast(int dia) {

		String clima = WEATHER_DRY;

		PlanetBetasoide pbeta = new PlanetBetasoide();
		PlanetFerengi pfere = new PlanetFerengi();
		PlanetVulcano pvul = new PlanetVulcano();

		int betaPositionGrad = pbeta.getOffsetInGrades(dia);
		////log.debug("grades beta " + betaPositionGrad);
		// calc offset 1
		Position posBeta = pbeta.getPosition(betaPositionGrad );
		//log.debug("beta: " + posBeta.getAxisX() + ", " + posBeta.getAxisY());
		
		
		int ferePositionGrad = pfere.getOffsetInGrades(dia);
		// calc offset 2
		Position posFere = pfere.getPosition(ferePositionGrad );
		//log.debug("grades fere " + ferePositionGrad);
		//log.debug("posFere: " + posFere.getAxisX() + ", " + posFere.getAxisY());
		
		int vulPositionGrad = pvul.getOffsetInGrades(dia);
		//log.debug("grades vul " + vulPositionGrad);
		// calc offset 3
		Position posVul = pvul.getPosition(vulPositionGrad );
		//log.debug("posVul: " + posVul.getAxisX() + ", " + posVul.getAxisY());

		boolean horizontal = posBeta.getAxisY() == posFere.getAxisY() && posFere.getAxisY() == posVul.getAxisY()
				&& (posVul.getAxisX() == 90 || posVul.getAxisX() == 270);
		boolean vertical = posBeta.getAxisX() == posFere.getAxisX() && posFere.getAxisX() == posVul.getAxisX()
				&& (posVul.getAxisX() == 0 || posVul.getAxisX() == 180);

		double yValue = getYvalueForX(posBeta, posFere, 0);

		double yValue2 = getYvalueForX(posVul, posFere, 0);

		double yValue3 = getYvalueForX(posVul, posBeta, 0);

		boolean diagonal = yValue == 0 && yValue2 == 0;

		if (horizontal || vertical || diagonal) {
			clima = WEATHER_RAIN;
		} else {

			// triang

			java.util.List<Position> positionYOrder = new ArrayList<Position>();
			positionYOrder.add(posFere);
			positionYOrder.add(posBeta);
			positionYOrder.add(posVul);

			Collections.sort(positionYOrder, new PositionYComparator());

			Position posYMax = positionYOrder.get(2);
			Position posYMed = positionYOrder.get(1);
			Position posYMin = positionYOrder.get(0);

			// condition y
			if (posYMax.getAxisY() > 0 && posYMin.getAxisX() < 0) {

				// condition x

				if (
				// different side y
				(posYMax.getAxisX() > 0 && posYMin.getAxisX() < 0) ||

				// different opposite side y
						(posYMax.getAxisX() < 0 && posYMin.getAxisX() > 0) ||

						// same side y but mid different side
						(posYMax.getAxisX() < 0 && posYMed.getAxisX() > 0) ||

						// same side y but mid different opposite side
						(posYMax.getAxisX() > 0 && posYMed.getAxisX() < 0)

				) {

					double yValueMaxMin = getYvalueForX(posYMax, posYMin, 0);
					double yValueMaxMed = getYvalueForX(posYMax, posYMed, 0);

					// Position y0-min, y0-med > 0
					if ((yValueMaxMin > 0 && yValueMaxMed < 0) || yValueMaxMin < 0 && yValueMaxMed > 0) {
						
						// perim max
						int lengthTotal = getMaxLenght(posYMax, posYMed, posYMin);
						

						clima = WEATHER_RAIN;

					}
				}
			} else {
				// Line up
				double yValueMaxMed = getYvalueForX(posYMax, posYMed, 0);

				double yValueMaxMin = getYvalueForX(posYMax, posYMin, 0);

				if (yValueMaxMed == yValueMaxMin && yValueMaxMed != 0) {
					clima = WEATHER_OPTIMUS;
				}
			}

		}

		return new DayForecast(dia, clima);
	}

	private int getMaxLenght(Position posYMax, Position posYMed, Position posYMin) {

		double longMaxMed = 0;
		double longMaxMedX = 0;
		
		double yMax = posYMax.getAxisY();
		double yMed = posYMed.getAxisY();
		
		double xMax = posYMax.getAxisX();
		double xMed = posYMed.getAxisX();
		
		if (yMax > 0 && yMed > 0) {
			longMaxMed = Math.pow(yMax + yMed , 2);
		}
		else {
			
		}
		
		if (xMax > 0 && xMed > 0) {
			longMaxMedX = Math.pow(xMax + xMed , 2);
		}	
		else {
			
		}
		
		
		
		
	return 0;
}

	private static double getYvalueForX(Position posA, Position posB, double x) {
		// verificar diagonal
		// (y -y1) / (y2 - y1) = (x -x1) / (x2 -x1)
		// y=0 si x=0

		double y1 = posA.getAxisY();
		double y2 = posB.getAxisY();

		double x1 = posA.getAxisX();
		double x2 = posB.getAxisX();

		double yDiff = y2 - y1;
		double xDiff = x2 - x1;

		// si x=0

		double x0 = x - 1 * posA.getAxisX();

		double term2 = x0 / xDiff;

		double yValue = (term2 * yDiff) + y1;
		return yValue;
	}

}
