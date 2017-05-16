package com.ml.rest.model;

public class DayForecast {


	private int dia;
    private String clima;

    public DayForecast(int dia) {
        this.dia = dia;
        
		
    }

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}


}
