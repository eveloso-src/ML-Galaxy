package com.ml.rest.interfaces;

import com.ml.rest.model.Position;

public interface PlanetMovement {
	
	public int getOffsetInGrades(int days);
	
	public Position getPosition(int gradesOffset);

	
}
