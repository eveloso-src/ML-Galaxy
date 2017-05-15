package com.ml.rest.util;

import java.util.Comparator;

import com.ml.rest.model.Position;

public class PositionYComparator implements Comparator<Position> {

	@Override
	public int compare(Position o1, Position o2) {
		return Double.valueOf(o1.getAxisY()).compareTo(Double.valueOf( o2.getAxisY()));
		
	}

}
