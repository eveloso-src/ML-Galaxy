package com.ml.rest.model;

import org.springframework.stereotype.Component;

@Component
public class PlanetVulcano extends PlanetBase{
	
	protected static int SPEED = -5;
	
	protected static int DISTANCE = 1000;

	@Override
	protected int getSpeed() {
		return SPEED;
	}

	@Override
	protected int getDistance() {
		return DISTANCE;
	}
	
	
	public int getOffsetInGrades(int days) {
		int fullMovement = Math.abs(days * getSpeed()) ;
		int grades = FULL_CYCLE - fullMovement;
		return grades;
	}


}
