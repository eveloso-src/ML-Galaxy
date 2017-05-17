package com.ml.rest.model;

public class Line {
	
	private Position pointA;
	private Position pointB;
	
	public Line(Position a, Position b) {
		pointA = a;
		pointB = b;
	}
	
	public Position getPointA() {
		return pointA;
	}
	public void setPointA(Position pointA) {
		this.pointA = pointA;
	}
	public Position getPointB() {
		return pointB;
	}
	public void setPointB(Position pointB) {
		this.pointB = pointB;
	}
	
	
	

}
