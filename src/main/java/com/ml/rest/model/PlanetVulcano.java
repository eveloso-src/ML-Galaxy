package com.ml.rest.model;

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
	
	@Override
	public int getOffsetInGrades(int days) {
		int fullMovement = Math.abs(days * getSpeed()) ;
		int grades = FULL_CICLE - fullMovement;
		return grades;
	}


}
