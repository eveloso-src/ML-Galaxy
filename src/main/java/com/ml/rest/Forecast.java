package com.ml.rest;

import com.ml.rest.model.PlanetBetasoide;
import com.ml.rest.model.PlanetFerengi;
import com.ml.rest.model.PlanetVulcano;
import com.ml.rest.model.Position;

public class Forecast {

    private static final String CLIMA_LLUVIA = "lluvia";
    private static final String CLIMA_SEQUIA = "sequia";
	private int dia;
    private String clima;

    public Forecast(int dia) {
        this.dia = dia;
        this.clima = CLIMA_LLUVIA;
        
        PlanetBetasoide pbeta = new PlanetBetasoide();
        PlanetFerengi pfere = new PlanetFerengi();
        PlanetVulcano pvul = new PlanetVulcano();
        
        // calcular desplazamiento 1
         Position posBeta = pbeta.getPosition(pbeta.getOffsetInGrades(dia));
        // calcular desplazamiento 2
         Position posFere = pbeta.getPosition(pfere.getOffsetInGrades(dia));
        // calcular desplazamiento 3
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
			this.clima = CLIMA_LLUVIA;
		}
		
		//triangulo
		boolean condBeta = getConditionMax(posBeta, posFere, posVul);
		
		boolean condFere = getConditionMax(posFere, posBeta, posVul);
		
		boolean condVul = getConditionMax(posVul, posFere, posBeta );
		
				
		if (condBeta) {
			
		}
		
    }

	private boolean getConditionMax(Position posA, Position posB, Position posC) {

		double yValue = posA.getAxisY();
		double yValue2 = posB.getAxisY();
		double yValue3 = posC.getAxisY();
		
		return yValue > 0 && yValue2 < 0 && yValue3 < 0 ||
				yValue < 0 && yValue2 > 0 && yValue3 > 0;
	}

	private boolean isPeriodoLluvia(Position posA, Position posB, Position posC) {
		boolean triang = false;
		
		if (posA.getAxisX() > posB.getAxisX() && posA.getAxisX() > posC.getAxisX()) {
			
			if (posB.getAxisX() < posC.getAxisX()) {
				// A B C
				
				
			}
			else {
				// A C B
				
				
				
			}
		}
		
		// + - -
		boolean cond1 = (posA.getAxisX() > 0 && posB.getAxisX() < 0 && posC.getAxisX() < 0) ;
		
		// + + -
		boolean cond2 = (posA.getAxisX() > 0 && posB.getAxisX() > 0 && posC.getAxisX() < 0) ;
		
		// + - +
		boolean cond3 = (posA.getAxisX() > 0 && posB.getAxisX() < 0 && posC.getAxisX() > 0) ;
		
		// - + +
		boolean cond4 = (posA.getAxisX() < 0 && posB.getAxisX() > 0 && posC.getAxisX() > 0) ;
		
		// - - +
		boolean cond5 = (posA.getAxisX() < 0 && posB.getAxisX() < 0 && posC.getAxisX() > 0) ;
		
		// - + -
		boolean cond6 = (posA.getAxisX() < 0 && posB.getAxisX() > 0 && posC.getAxisX() < 0) ;

		if (cond1){
			
		}
		return triang;
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
