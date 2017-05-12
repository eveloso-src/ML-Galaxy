package com.ml.rest.model;

import com.ml.rest.interfaces.PlanetMovement;

public abstract class PlanetBase implements PlanetMovement {
	
	protected int speedPerSecond;
	
	protected abstract int getSpeed();
	
	protected abstract Position getPosition();
	
	

	public int getSpeedPerSecond() {
		return speedPerSecond;
	}

	public void setSpeedPerSecond(int speedPerSecond) {
		this.speedPerSecond = speedPerSecond;
	}
	
	

}
