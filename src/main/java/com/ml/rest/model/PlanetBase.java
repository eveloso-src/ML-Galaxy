package com.ml.rest.model;

import com.ml.rest.interfaces.PlanetMovement;

public abstract class PlanetBase implements PlanetMovement {
	
	protected static final int FULL_CYCLE = 360;
	protected abstract int getSpeed();	
	protected abstract int getDistance();
	
	
	public Position getPosition(int gradesOffset) {
		
		
		double opposite = 0;
		if (gradesOffset < 90 ) {
			// cateto opuesto
			opposite = gradesOffset;
			
		}
		else if (gradesOffset > 90  && gradesOffset < 180) { 
			opposite = 180 - gradesOffset;
		}
		else if (gradesOffset > 180  && gradesOffset < 270) { 
			opposite = gradesOffset - 180;
		}		
		else if (gradesOffset > 270 && gradesOffset < FULL_CYCLE) { 
			opposite = FULL_CYCLE  - gradesOffset ;
		}		
		
		
		double sen =  Math.sin(Math.toRadians(opposite));
		double catetoX = sen * getDistance();
		
		// pitagoras: o2 + a2 = h2
		
		double catetoOP2 = Math.pow(catetoX, 2);
		double hipo2 = Math.pow(getDistance(), 2);
		double adyac = Math.sqrt(hipo2 - catetoOP2);

		return new Position( Math.round(catetoX), Math.round(adyac), gradesOffset);
	}
	
	

	
}
