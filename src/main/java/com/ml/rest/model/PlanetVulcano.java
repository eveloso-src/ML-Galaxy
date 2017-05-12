package com.ml.rest.model;

public class PlanetVulcano extends PlanetBase{
	
	protected static int SPEED = -5;
	
	protected static int DISTANCE = 1000;

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
