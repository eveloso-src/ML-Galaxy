package com.ml.rest.model;

import org.springframework.stereotype.Component;

@Component
public class PlanetFerengi extends PlanetBase{
	
	protected static int SPEED = 1;
	
	protected static int DISTANCE = 500;

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
