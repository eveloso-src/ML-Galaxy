package com.ml.rest.model;

public class Position {
	
	private double axisX;
	private double axisY;
	
	public Position(double axisX, double axisY) {
		this.axisX = axisX;
		this.axisY = axisY;
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
	
	

}
