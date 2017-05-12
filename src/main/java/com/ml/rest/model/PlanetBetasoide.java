package com.ml.rest.model;

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
	

}
