package com.ml.rest.model;

import com.ml.rest.interfaces.PlanetMovement;

public abstract class PlanetBase implements PlanetMovement {
	
	protected static final int FULL_CYCLE = 360;
	protected abstract int getSpeed();	
	protected abstract int getDistance();
	
	
	public Position getPosition(int gradesOffset) {
		double b = Math.toRadians(gradesOffset);
		
		// cateto opuesto
		double opposite = Math.sin(b);
		
		// pitagoras: o2 + a2 = h2
		
		double catetoOP2 = Math.pow(opposite, 2);
		double hipo2 = Math.pow(getDistance(), 2);
		double adyac = Math.sqrt(hipo2 + catetoOP2);

		return new Position(catetoOP2, adyac, gradesOffset);
	}
	
	
	public int getOffsetInGrades(int days) {
		int fullMovement = (days * getSpeed()) ;
		return fullMovement % FULL_CYCLE;
	}
	
}
