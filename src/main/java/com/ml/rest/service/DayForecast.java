package com.ml.rest.service;

import java.util.ArrayList;
import java.util.Collections;

import com.ml.rest.model.PlanetBetasoide;
import com.ml.rest.model.PlanetFerengi;
import com.ml.rest.model.PlanetVulcano;
import com.ml.rest.model.Position;
import com.ml.rest.util.PositionYComparator;

public class DayForecast {

    private static final String WEATHER_RAIN = "lluvia";
    private static final String WEATHER_OPTIMUS = "optimo";
    private static final String WEATHER_DRY = "sequia";
	private int dia;
    private String clima;

    public DayForecast(int dia) {
        this.dia = dia;
        this.clima = WEATHER_DRY;
        
        PlanetBetasoide pbeta = new PlanetBetasoide();
        PlanetFerengi pfere = new PlanetFerengi();
        PlanetVulcano pvul = new PlanetVulcano();
        
        // calc offset 1
         Position posBeta = pbeta.getPosition(pbeta.getOffsetInGrades(dia));
        // calc offset 2
         Position posFere = pbeta.getPosition(pfere.getOffsetInGrades(dia));
        // calc offset 3
         Position posVul = pbeta.getPosition(pvul.getOffsetInGrades(dia));
        
        boolean horizontal = posBeta.getAxisX() == posFere.getAxisX() && posFere.getAxisX() == posVul.getAxisX() && 
        		 (posVul.getAxisX() == 90 ||  posVul.getAxisX() == 270);
		boolean vertical = posBeta.getAxisY() == posFere.getAxisY() && posFere.getAxisY() == posVul.getAxisY()
				&& (posVul.getAxisX() == 0 || posVul.getAxisX() == 180);

		double yValue = getYvalueForX(posBeta, posFere, 0);
		
		double yValue2 = getYvalueForX(posVul, posFere, 0);
		
		double yValue3 = getYvalueForX(posVul, posBeta, 0);
		
		boolean diagonal = yValue == 0 && yValue2 == 0;

		if (horizontal || vertical || diagonal) {
			this.clima = WEATHER_RAIN;
		}
		else {
			
			//triang
			
			java.util.List<Position> positionYOrder = new ArrayList<Position>();
			
			Collections.sort(positionYOrder, new PositionYComparator()) ;
			
			Position posYMax = positionYOrder.get(0);
			Position posYMed = positionYOrder.get(1);
			Position posYMin = positionYOrder.get(2);
			
			// condition y 
			if (posYMax.getAxisY() > 0 &&  posYMin.getAxisX() < 0) {
				
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
					if ( (yValueMaxMin > 0 && yValueMaxMed < 0) ||
							yValueMaxMin < 0 && yValueMaxMed > 0 ){
						
						this.clima = WEATHER_RAIN;
						
					}				
				}			
			}
			else {
				// Line up
				double yValueMaxMed = getYvalueForX(posYMax, posYMed, posYMax.getAxisX());
				
				double yValueMaxMin = getYvalueForX(posYMax, posYMin, posYMax.getAxisX());
				
				if (yValueMaxMed == yValueMaxMin && yValueMaxMed != 0) {
					this.clima = WEATHER_OPTIMUS;
				}
				
				
				
			}
			
			
			
		}
		
    }

	

	private double getYvalueForX(Position posA, Position posB, double x) {
		//verificar diagonal
		//(y -y1) / (y2 - y1) = (x -x1) / (x2 -x1)
		// y=0 si x=0
		
		double y1 = posA.getAxisY();
		double y2 = posB.getAxisY();
		
		double x1 = posA.getAxisX();
		double x2 = posB.getAxisX();
		
		double yDiff = y2 - y1;
		double xDiff = x2 - x1;
		
		// si x=0
		
		double x0 = x -1 * posA.getAxisX();
		
		double term2 = x0 / xDiff;
		
		double yValue = (term2 * yDiff) + y1;
		return yValue;
	}

	public long getDia() {
		return dia;
	}

	public String getClima() {
		return clima;
	}

}
