package com.ml.rest.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ml.rest.model.Line;
import com.ml.rest.model.Position;


@Component("geoService")
public class GeometricService {
	
	public double getPerimeter(List<Line> lines ) { 

		Position posA;
		Position posB;
		
		double counter = 0;
		
		for (Line line : lines) {
			posA = line.getPointA();
			posB = line.getPointB();
			counter += getDisntance(posA, posB);
		}
		
		return counter;
	}

	public double getDisntance(Position posA, Position posB) {
		
		double longMaxMed = 0;
		double longMaxMedX = 0;
		
		double yMax = posA.getAxisY();
		double yMed = posB.getAxisY();
		
		double xMax = posA.getAxisX();
		double xMed = posB.getAxisX();

		if (yMax > 0 && yMed < 0) {
			yMed = yMed * -1;
		} 
		else if (xMax > 0 && xMed < 0) {
			xMed = xMed * -1;
		}
		
		
		longMaxMed = Math.pow(yMax + yMed , 2);
		longMaxMedX = Math.pow(xMed + xMax , 2);
		
		return Math.sqrt(longMaxMedX + longMaxMed);
	}

	public double getYvalueForX(Position posA, Position posB, double x) {
		// verificar diagonal
		// (y -y1) / (y2 - y1) = (x -x1) / (x2 -x1)
		// y=0 si x=0

		double y1 = posA.getAxisY();
		double y2 = posB.getAxisY();

		double x1 = posA.getAxisX();
		double x2 = posB.getAxisX();

		double yDiff = y2 - y1;
		double xDiff = x2 - x1;

		// si x=0

		double x0 = x - 1 * posA.getAxisX();

		double term2 = x0 / xDiff;

		double yValue = (term2 * yDiff) + y1;
		return yValue;
	}

}
