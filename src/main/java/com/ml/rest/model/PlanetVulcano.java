package com.ml.rest.model;

public class PlanetVulcano extends PlanetBase{
	
	protected static int SPEED = -5;

	@Override
	protected int getSpeed() {
		// TODO Auto-generated method stub
		return SPEED;
	}

	@Override
	protected Position getPosition() {
		// TODO Auto-generated method stub
		return new Position();
	}

}
