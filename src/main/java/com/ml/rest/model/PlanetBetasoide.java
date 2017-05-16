package com.ml.rest.model;

import org.springframework.stereotype.Component;

@Component
public class PlanetBetasoide extends PlanetBase{
	
	protected static int SPEED = 3;
	
	protected static int DISTANCE = 2000;

	@Override
	protected int getSpeed() {
		return SPEED;
	}

	@Override
	protected int getDistance() {
		return DISTANCE;
	}
	
	public int getOffsetInGrades(int days) {
		int fullMovement = (days * getSpeed()) ;
		return fullMovement % FULL_CYCLE;
	}
	

}
