package com.ml.rest.model;

public class PlanetFerengi extends PlanetBase{
	
	protected static int SPEED = 3;

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
