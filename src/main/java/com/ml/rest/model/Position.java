package com.ml.rest.model;

public class Position {
	
	private double axisX;
	private double axisY;
	private double angle;
	
	public Position(double axisX, double axisY, double grad) {
		this.axisX = axisX;
		this.axisY = axisY;
		this.angle = grad;
	}
	
	public double getAxisX() {
		return axisX;
	}
	public void setAxisX(double axisX) {
		this.axisX = axisX;
	}
	public double getAxisY() {
		return axisY;
	}
	public void setAxisY(double axisY) {
		this.axisY = axisY;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	
	

}
