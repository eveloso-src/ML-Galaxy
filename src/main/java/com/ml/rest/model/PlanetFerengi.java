package com.ml.rest.model;

public class PlanetFerengi extends PlanetBase{
	
	protected static int SPEED = 1;
	
	protected static int DISTANCE = 500;

	@Override
	protected int getSpeed() {
		// TODO Auto-generated method stub
		return SPEED;
	}

	@Override
	protected int getDistance() {
		
		return DISTANCE;
	}

}
